package com.konkuk.kuit_kac.presentation.onboarding

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.Agreement
import com.konkuk.kuit_kac.data.request.OnboardingRequestDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) : ViewModel() {

    private val _draft = mutableStateOf(OnboardingDraft())
    val draft: State<OnboardingDraft> get() = _draft

    fun setAge(age: Int) { _draft.value = _draft.value.copy(age = age) }
    fun setGender(gender: String) { _draft.value = _draft.value.copy(gender = gender) }
    fun setHeight(height: Int) { _draft.value = _draft.value.copy(height = height) }
    fun setNickname(nickname: String) { _draft.value = _draft.value.copy(nickname = nickname) }
    fun setTargetWeight(w: Double) { _draft.value = _draft.value.copy(targetWeight = w) }
    fun setWeeklyEatingOutCount(v: String) { _draft.value = _draft.value.copy(weeklyEatingOutCount = v) }
    fun setAppetiteType(v: String) { _draft.value = _draft.value.copy(appetiteType = v) }
    fun setDietFailReason(v: String) { _draft.value = _draft.value.copy(dietFailReason = v) }
    fun setDietVelocity(v: String) { _draft.value = _draft.value.copy(dietVelocity = v) }
    fun setEatingOutType(v: String) { _draft.value = _draft.value.copy(eatingOutType = v) }
    fun setHasDietExperience(b: Boolean) { _draft.value = _draft.value.copy(hasDietExperience = b) }


    private val _agreements = mutableStateListOf<Agreement>()   // no null seed
    val agreements: List<Agreement> get() = _agreements

    fun setAgreements(list: List<Agreement>) {
        _agreements.clear()
        _agreements.addAll(list)
    }

    fun upsertAgreement(code: String, agreed: Boolean, version: String) {
        val idx = _agreements.indexOfFirst { it.code == code }
        if (idx >= 0) {
            _agreements[idx] = _agreements[idx].copy(agreed = agreed, version = version)
        } else {
            _agreements.add(Agreement(agreed = agreed, code = code, version = version))
        }
    }

    private val _onboardingPostSuccessState = mutableStateOf<Boolean?>(null) // null = idle/loading
    val onboardingPostSuccessState: State<Boolean?> get() = _onboardingPostSuccessState

    private fun submit(agreements: List<Agreement>): OnboardingRequestDto? {
        val d = _draft.value
        val requiredFilled =
            d.age != null && d.gender != null && d.height != null && d.nickname != null &&
                    d.targetWeight != null && d.weeklyEatingOutCount != null &&
                    d.appetiteType != null &&
                    d.dietVelocity != null && d.eatingOutType != null && d.hasDietExperience != null

        if (!requiredFilled) return null

        return OnboardingRequestDto(
            age = d.age!!,
            agreements = agreements,
            appetiteType = d.appetiteType!!,
            dietFailReason = d.dietFailReason,
            dietVelocity = d.dietVelocity!!,
            eatingOutType = d.eatingOutType!!,
            gender = d.gender!!,
            hasDietExperience = d.hasDietExperience!!,
            height = d.height!!,
            nickname = d.nickname!!,
            targetWeight = d.targetWeight!!,
            weeklyEatingOutCount = d.weeklyEatingOutCount!!
        )
    }

    fun postOnboarding() {
        val request = submit(_agreements.toList())

        if (request == null) {
            _onboardingPostSuccessState.value = false
            Log.e("postOnboarding", "Missing required fields: ${missingFields().joinToString()}")
            return
        }

        viewModelScope.launch {
            _onboardingPostSuccessState.value = null
            runCatching { onboardingRepository.postOnboarding(request) }
                .onSuccess {
                    _onboardingPostSuccessState.value = true
                    Log.d("postOnboarding", "success")
                }
                .onFailure {
                    _onboardingPostSuccessState.value = false
                    Log.e("postOnboarding", it.message ?: "Unknown error")
                }
        }
    }

    private fun missingFields(): List<String> {
        val d = _draft.value
        return buildList {
            if (d.age == null) add("age")
            if (d.gender == null) add("gender")
            if (d.height == null) add("height")
            if (d.nickname == null) add("nickname")
            if (d.targetWeight == null) add("targetWeight")
            if (d.weeklyEatingOutCount == null) add("weeklyEatingOutCount")
            if (d.appetiteType == null) add("appetiteType")
            if (d.dietVelocity == null) add("dietVelocity")
            if (d.eatingOutType == null) add("eatingOutType")
            if (d.hasDietExperience == null) add("hasDietExperience")
        }
    }
}

data class OnboardingDraft(
    val age: Int? = null,
    val appetiteType: String? = null,
    val dietFailReason: String = "",
    val dietVelocity: String? = null,
    val eatingOutType: String? = null,
    val gender: String? = null,
    val hasDietExperience: Boolean? = null,
    val height: Int? = null,
    val nickname: String? = null,
    val targetWeight: Double? = null,
    val weeklyEatingOutCount: String? = null
)
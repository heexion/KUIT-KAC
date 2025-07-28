package com.konkuk.kuit_kac.presentation.mealdiet.diet.viewmodel

import android.util.Log
import android.view.View
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.FoodRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.service.DietService
import com.konkuk.kuit_kac.presentation.mealdiet.diet.repository.DietRepository
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

class DietViewModel @Inject constructor(
    private val dietRepository: DietRepository
):ViewModel() {
    private val _createDietState = mutableStateOf<Boolean?>(null)
    val createDietState: State<Boolean?> get() = _createDietState
}
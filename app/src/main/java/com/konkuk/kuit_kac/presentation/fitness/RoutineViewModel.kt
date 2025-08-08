package com.konkuk.kuit_kac.presentation.fitness

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.DietRequestDto
import com.konkuk.kuit_kac.data.request.FitnessRequestDto
import com.konkuk.kuit_kac.data.request.FoodRequestDto
import com.konkuk.kuit_kac.data.request.RoutineRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.data.response.RoutineResponseDto
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.fitness.local.FitnessRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel.FoodWithQuantity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val fitnessRepository: FitnessRepository,
    private val routineRepository: RoutineRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _createRoutineSuccessState = mutableStateOf<Boolean?>(null)
    val createRoutineSuccessState: State<Boolean?> get() = _createRoutineSuccessState
    private val _selectedRoutines = mutableStateListOf<Fitness?>()
    val selectedRoutines: List<Fitness?> get() = _selectedRoutines
    private val _routineName = mutableStateOf<String?>(null)
    val routineName: State<String?> get() = _routineName
    private val _routineType = mutableStateOf<String?>(null)
    val routineType: State<String?> get() = _routineType
    private val _getRoutineRecordState =mutableStateOf<List<RoutineResponseDto>?>(null)
    val getRoutineRecordState: State<List<RoutineResponseDto>?> get() = _getRoutineRecordState
    private val _getRoutineRecordSuccessState = mutableStateOf<Boolean?>(null)
    val getRoutineRecordSuccessState: State<Boolean?> get() = _getRoutineRecordSuccessState
    private val _routineId = mutableStateOf<Int?>(null)
    val routineId: State<Int?> get() = _routineId

    private val _initialName = mutableStateOf<String?>(null)
    val initialName: State<String?> get() = _initialName
    private val _exerciseRecords = androidx.compose.runtime.mutableStateMapOf<Int, ExerciseRecord>()
    val exerciseRecords: Map<Int, ExerciseRecord> get() = _exerciseRecords

    init {
        val idArg = savedStateHandle.get<Int>("routineId") ?: -1
        val nameArg = savedStateHandle.get<String>("name") ?: ""
        _routineId.value = if (idArg >= 0) idArg else null
        _initialName.value = if (nameArg.isNotBlank()) nameArg else null
        _routineName.value = _initialName.value
        _routineId.value?.let { id ->
            viewModelScope.launch {
                runCatching {
                    // Option A: fetch all templates then pick by id
                    val resp = routineRepository.getRoutineTemplate(userId = 1)
                    resp.body()?.firstOrNull { it.id == id }
                        ?: error("No template $id")
                }.onSuccess { template ->
                    template.routineExerciseProfiles.forEach { profile ->
                        val f = Fitness(
                            id = profile.exercise.id,
                            name = profile.exercise.name,
                            targetMuscleGroup = profile.exercise.targetMuscleGroup,
                            metValue = profile.exercise.metValue.toDouble(),
                            type = 0
                        )
                        _selectedRoutines.add(f)
                    }
                    _routineType.value = template.routineType
                }.onFailure {
                    Log.e("RoutineVM", "failed loading template $id", it)
                }
            }
        }
        }

        fun createRoutine(
        ) {
            val routines = selectedRoutines.map {
                FitnessRequestDto(
                    exerciseId = it?.id ?: -1
                )
            }
            val request = RoutineRequestDto(
                userId = 1,
                name = routineName.value ?: "",
                routineType = routineType.value ?: "",
                routineExercises = routines
            )
            viewModelScope.launch {
                runCatching {
                    routineRepository.createRoutine(request)
                }
                    .onSuccess {
                        Log.d("API", "Success")
                        _createRoutineSuccessState.value = true
                        Log.e("createRoutine", "success")
                    }
                    .onFailure {
                        _createRoutineSuccessState.value = false
                        Log.e("creatDiet", it.message ?: "Unknown error")
                    }
            }
        }

        fun getRoutineRecord(userId: Int) {
            viewModelScope.launch {
                runCatching {
                    routineRepository.getRoutineRecord(userId)
                }.onSuccess { response ->
                    if (response.isSuccessful) {
                        val body = response.body()
                        _getRoutineRecordState.value = body
                        _getRoutineRecordSuccessState.value = true
                    } else {
                        _getRoutineRecordSuccessState.value = false
                        Log.e("getRoutineRecord", "Unsuccessful response: ${response.code()}")
                    }
                }.onFailure {
                    _getRoutineRecordSuccessState.value = false
                    Log.e("getRoutineRecord", it.message ?: "Unknown error")
                }
            }
        }
    private val _changeRoutineRecordSuccessState = mutableStateOf<Boolean?>(null)
    val changeRoutineRecordSuccessState: State<Boolean?> get() = _changeRoutineRecordSuccessState
    fun changeRoutine(

    ){
        val routines = selectedRoutines.map {
            FitnessRequestDto(
                exerciseId = it?.id ?: -1
            )
        }
        val request = RoutineRequestDto(
            userId = 1,
            name = routineName.value ?: "",
            routineType = routineType.value ?: "",
            routineExercises = routines
        )
        viewModelScope.launch {
            viewModelScope.launch {
                runCatching {
                    routineRepository.changeRoutine(dietId = routineId.value?:-1,request)
                }
                    .onSuccess {
                        Log.d("API", "Success")
                        _changeRoutineRecordSuccessState.value= true
                        Log.e("changeRoutineRecord", "success")
                    }
                    .onFailure {
                        _changeRoutineRecordSuccessState.value = false
                        Log.e("changeRoutineRecord", it.message ?: "Unknown error")
                    }
            }
        }
    }

        private val _getRoutineTemplateState = mutableStateOf<List<RoutineResponseDto>?>(null)
        val getRoutineTemplateState: State<List<RoutineResponseDto>?> get() = _getRoutineTemplateState
        private val _getRoutineTemplateSuccessState = mutableStateOf<Boolean?>(null)
        val getRoutineTemplateSuccessState: State<Boolean?> get() = _getRoutineTemplateSuccessState
        fun getRoutineTemplate(userId: Int) {
            viewModelScope.launch {
                runCatching {
                    routineRepository.getRoutineTemplate(userId)
                }.onSuccess { response ->
                    if (response.isSuccessful) {
                        val body = response.body()
                        _getRoutineTemplateState.value = body
                        _getRoutineTemplateSuccessState.value = true
                    } else {
                        _getRoutineTemplateSuccessState.value = false
                        Log.e("getRoutineTemplate", "Unsuccessful response: ${response.code()}")
                    }
                }.onFailure {
                    _getRoutineTemplateSuccessState.value = false
                    Log.e("getRoutineTemplate", it.message ?: "Unknown error")
                }
            }
        }

        fun setName(name: String) {
            _routineName.value = name
        }

        fun addRoutine(routine: Fitness) {
            _selectedRoutines.add(routine)
        }

        fun setType(type: String) {
            _routineType.value = type
        }

        fun removeRoutine(item: Fitness) {
            _selectedRoutines.remove(item)
        }
    fun setSelectedRoutines(items: List<Fitness>) {
        _selectedRoutines.clear()
        _selectedRoutines.addAll(items)
        items.forEach { f ->
            if (!_exerciseRecords.containsKey(f.id)) {
                _exerciseRecords[f.id] = ExerciseRecord()
            }
        }
        _exerciseRecords.keys
            .filter { id -> items.none { it.id == id } }
            .forEach { _exerciseRecords.remove(it) }
    }
    fun getRecord(id: Int): ExerciseRecord = _exerciseRecords[id] ?: ExerciseRecord()

    fun updateMinutes(id: Int, minutesStr: String) {
        val m = minutesStr.toIntOrNull() ?: 0
        _exerciseRecords[id] = getRecord(id).copy(minutes = m)
    }

    fun updateIntensity(id: Int, intensity: Int) {
        _exerciseRecords[id] = getRecord(id).copy(intensity = intensity)
    }

    fun updateDetail(id: Int, detail: String) {
        _exerciseRecords[id] = getRecord(id).copy(detail = detail)
    }
    fun totalKcal(): Int {
        return selectedRoutines.filterNotNull().sumOf { f ->
            val r = _exerciseRecords[f.id] ?: return@sumOf 0
            val factor = when (r.intensity) { 0 -> 3; 1 -> 5; 2 -> 8; else -> 0 }
            r.minutes * factor
        }
    }
    fun setRoutineId(id: Int?) { _routineId.value = id }
    fun kcalFor(id: Int): Int {
        val r = getRecord(id)
        val factor = when (r.intensity) {
            0 -> 3   // low
            1 -> 5   // medium
            2 -> 8   // high
            else -> 0
        }
        return r.minutes * factor
    }
    fun ensureExercise(f: Fitness) {
        if (selectedRoutines.none { it?.id == f.id }) {
            _selectedRoutines.add(f)
        }
        _exerciseRecords.putIfAbsent(f.id, ExerciseRecord())
    }

}

data class ExerciseRecord(
    val minutes: Int = 0,
    val intensity: Int = -1,
    val detail: String = ""
)
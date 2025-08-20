package com.konkuk.kuit_kac.presentation.fitness
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.data.request.ExerciseRequestDto
import com.konkuk.kuit_kac.data.request.FitnessRequestDto
import com.konkuk.kuit_kac.data.request.RecordRequestDto
import com.konkuk.kuit_kac.data.request.RoutineDetailDto
import com.konkuk.kuit_kac.data.request.RoutineRequestDto
import com.konkuk.kuit_kac.data.request.RoutineSetsDto
import com.konkuk.kuit_kac.data.response.RecordResponseDto
import com.konkuk.kuit_kac.data.response.RoutineResponseDto
import com.konkuk.kuit_kac.local.Fitness
import com.konkuk.kuit_kac.presentation.fitness.RoutineRepository
import com.konkuk.kuit_kac.presentation.fitness.local.FitnessRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.Normalizer
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
    private val _getRoutineRecordState = mutableStateOf<List<RecordResponseDto>?>(null)
    val getRoutineRecordState: State<List<RecordResponseDto>?> get() = _getRoutineRecordState
    private val _getRoutineRecordSuccessState = mutableStateOf<Boolean?>(null)
    val getRoutineRecordSuccessState: State<Boolean?> get() = _getRoutineRecordSuccessState
    private val _routineId = mutableStateOf<Int?>(null)
    val routineId: State<Int?> get() = _routineId

    private val _initialName = mutableStateOf<String?>(null)
    val initialName: State<String?> get() = _initialName
    private val _exerciseRecords = androidx.compose.runtime.mutableStateMapOf<Int, ExerciseRecord>()
    val exerciseRecords: Map<Int, ExerciseRecord> get() = _exerciseRecords
    private fun intensityIndexFromString(s: String) = when (s) {
        "느슨함" -> 0
        "보통"   -> 1
        "강함" -> 2
        else -> 1
    }
    private fun intensityStringFromIndex(i: Int) = when (i) {
        0 -> "느슨함"
        1 -> "보통"
        2 -> "강함"
        else -> "보통"
    }

    fun seedFromRecord(record: RecordResponseDto) {
        _routineName.value = record.name
        _routineType.value = record.routineType

        _selectedRoutines.clear()
        record.routineExerciseProfiles.forEach { p ->
            val f = Fitness(
                id = p.exercise.id,
                name = p.exercise.name,
                targetMuscleGroup = p.exercise.targetMuscleType,
                metValue = p.exercise.metValue.toDouble()
            )
            ensureExercise(f)

            _exerciseRecords[f.id] = ExerciseRecord(
                minutes = p.routineDetail.time,
                intensity = intensityIndexFromString(p.routineDetail.intensity),
                detail = ""
            )

            val mappedSets = p.routineSets.map { s ->
                RoutineSetsDto(
                    count = s.count,
                    distance = s.distance,
                    time = s.time,
                    weightKg = s.weightKg,
                    weightNum = s.weightNum,
                    setOrder = s.setOrder
                )
            }.toMutableList()
            _setsByExerciseName[f.name] = mappedSets
        }
        val validNames = _selectedRoutines.filterNotNull().map { it.name }.toSet()
        _setsByExerciseName.keys.filter { it !in validNames }.toList().forEach { _setsByExerciseName.remove(it) }
    }


    private fun RoutineResponseDto.RoutineExerciseProfile.toFitness(): Fitness =
        Fitness(
            id = exercise.id,
            name = exercise.name,
            targetMuscleGroup = exercise.targetMuscleGroup,
            metValue = exercise.metValue
        )
    private fun RecordResponseDto.RoutineExerciseProfile.toFitness(): Fitness =
        Fitness(
            id = exercise.id,
            name = exercise.name,
            targetMuscleGroup = exercise.targetMuscleType,
            metValue = exercise.metValue.toDouble()
        )

    init {
        val idArg = savedStateHandle.get<Int>("routineId") ?: -1
        val nameArg = savedStateHandle.get<String>("name") ?: ""
        _routineId.value = if (idArg >= 0) idArg else null
        _initialName.value = if (nameArg.isNotBlank()) nameArg else null
        _routineName.value = _initialName.value

        _routineId.value?.let { id ->
            viewModelScope.launch {
                runCatching {
                    val resp = routineRepository.getRoutineTemplate(userId = 1)
                    resp.body()?.firstOrNull { it.id == id } ?: error("No template $id")
                }.onSuccess { template ->
                    template.routineExerciseProfiles.forEach { profile ->
                        _selectedRoutines.add(profile.toFitness())
                    }
                    _routineType.value = template.routineType
                }.onFailure {
                    Log.e("RoutineVM", "failed loading template $id", it)
                }
            }
        }
    }
    private fun buildExerciseRequests(): List<ExerciseRequestDto> {
        return _selectedRoutines.filterNotNull().map { f ->
            val rec = getRecord(f.id)
            val sets = setsByExerciseName[f.name].orEmpty()
            Log.d("BuildRequest", "Exercise: ${f.name}, Sets: $sets")
            ExerciseRequestDto(
                exerciseId = f.id,
                routineDetail = RoutineDetailDto(
                    time = rec.minutes,
                    intensity = intensityStringFromIndex(rec.intensity)
                ),
                routineSets = sets
            )
        }
    }
    private fun buildRecordRequest(): RecordRequestDto {
        return RecordRequestDto(
            name = _routineName.value.orEmpty(),
            routineType = _routineType.value ?: "기록",
            userId = 1,
            routineExercises = buildExerciseRequests()
        )
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

    private val _updateRoutineRecordSuccessState = mutableStateOf<Boolean?>(null)
    val updateRoutineRecordSuccessState: State<Boolean?> get() = _updateRoutineRecordSuccessState

    fun changeRecordRoutine() {
        val id = _routineId.value ?: run {
            Log.e("changeRecordRoutine", "No routineId set")
            _updateRoutineRecordSuccessState.value = false
            return
        }
        val body = buildRecordRequest()
        viewModelScope.launch {
            runCatching { routineRepository.changeRecordRoutine(id, body) }
                .onSuccess { resp ->
                    if (resp.isSuccessful) {
                        _updateRoutineRecordSuccessState.value = true
                        Log.d("changeRecordRoutine", "success")
                    } else {
                        _updateRoutineRecordSuccessState.value = false
                        Log.e("changeRecordRoutine", "HTTP ${resp.code()}")
                    }
                }
                .onFailure {
                    _updateRoutineRecordSuccessState.value = false
                    Log.e("changeRecordRoutine", it.message ?: "Unknown error")
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
        _exerciseRecords.remove(item.id)
        _setsByExerciseName.remove(item.name)
    }
    fun setSelectedRoutines(items: List<Fitness>) {
        _selectedRoutines.clear()
        _selectedRoutines.addAll(items)

        items.forEach { f ->
            if (!_exerciseRecords.containsKey(f.id)) {
                _exerciseRecords[f.id] = ExerciseRecord()
            }
        }
        val validIds = items.map { it.id }.toSet()
        _exerciseRecords.keys
            .filter { it !in validIds }
            .toList()
            .forEach { _exerciseRecords.remove(it) }
        val validNames = items.map { it.name }.toSet()
        _setsByExerciseName.keys
            .filter { it !in validNames }
            .toList()
            .forEach { _setsByExerciseName.remove(it) }
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
    fun appendSetsByName(name: String, newSets: List<RoutineSetsDto>) {
        val merged = (_setsByExerciseName[name].orEmpty() + newSets)
            .mapIndexed { i, s -> s.copy(setOrder = i + 1) }
            .toMutableList()
        _setsByExerciseName[name] = merged
    }

    private val _setsByExerciseName = mutableStateMapOf<String, MutableList<RoutineSetsDto>>()
    val setsByExerciseName: Map<String, List<RoutineSetsDto>> get() = _setsByExerciseName

    fun addOrReplaceSetsByName(name: String, sets: List<RoutineSetsDto>) {
        _setsByExerciseName[name] = sets.toMutableList()
    }
    fun addSetByName(name: String, set: RoutineSetsDto) {
        val list = _setsByExerciseName.getOrPut(name) { mutableListOf() }
        list.add(set)
    }
    fun removeSetByName(name: String, index: Int) {
        _setsByExerciseName[name]?.let {
            if (index in it.indices) it.removeAt(index)
        }
    }
    fun createRoutineRecord() {
        val selected = _selectedRoutines.filterNotNull()
        if (selected.isEmpty()) {
            Log.e("createRoutineRecord", "No exercises selected")
            _createRoutineSuccessState.value = false
            return
        }
        val body = buildRecordRequest()

        val routineName = _routineName.value.orEmpty()
        val routineType = _routineType.value ?: "기록"

        viewModelScope.launch {
            runCatching {
                routineRepository.createRoutineRecord(
                    body
                )
            }.onSuccess { resp ->
                if (resp.isSuccessful) {
                    Log.d("createRoutineRecord", "success")
                    _createRoutineSuccessState.value = true
                } else {
                    Log.e("createRoutineRecord", "HTTP ${resp.code()}")
                    _createRoutineSuccessState.value = false
                }
            }.onFailure {
                Log.e("createRoutineRecord", it.message ?: "Unknown error")
                _createRoutineSuccessState.value = false
            }
        }
    }
    private fun idForName(name: String): Int? =
        _selectedRoutines.filterNotNull().firstOrNull { it.name == name }?.id
    fun rGetRecord(name: String): ExerciseRecord =
        idForName(name)?.let { getRecord(it) } ?: ExerciseRecord()

    fun rUpdateMinutes(name: String, minutesStr: String) {
        idForName(name)?.let { updateMinutes(it, minutesStr) }
    }

    fun rUpdateIntensity(name: String, intensity: Int) {
        idForName(name)?.let { updateIntensity(it, intensity) }
    }

    fun rUpdateDetail(name: String, detail: String) {
        idForName(name)?.let { updateDetail(it, detail) }
    }

    fun rKcalFor(name: String): Int =
        idForName(name)?.let { kcalFor(it) } ?: 0

}

data class ExerciseRecord(
    val minutes: Int = 0,
    val intensity: Int = -1,
    val detail: String = ""
)
package com.konkuk.kuit_kac.presentation.mealdiet.meal.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.kuit_kac.core.util.context.toDrawable
import com.konkuk.kuit_kac.data.request.AiDto
import com.konkuk.kuit_kac.data.request.AiRequestDto
import com.konkuk.kuit_kac.data.request.FoodRequestDto
import com.konkuk.kuit_kac.data.request.MealRequestDto
import com.konkuk.kuit_kac.data.request.PlanRequestDto
import com.konkuk.kuit_kac.data.request.SimpleRequestDto
import com.konkuk.kuit_kac.data.request.SnackFoodRequestDto
import com.konkuk.kuit_kac.data.request.SnackRequestDto
import com.konkuk.kuit_kac.data.response.MealResponseDto
import com.konkuk.kuit_kac.data.response.PlanResponseDto
import com.konkuk.kuit_kac.local.Food
import com.konkuk.kuit_kac.presentation.mealdiet.local.FoodRepository
import com.konkuk.kuit_kac.presentation.mealdiet.meal.MealRepository
import com.konkuk.kuit_kac.presentation.mealdiet.plan.PlanTagType
import com.konkuk.kuit_kac.presentation.mealdiet.plan.component.DietType
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mealRepository: MealRepository,
    private val foodRepository: FoodRepository,
    @ApplicationContext private val context: Context,
    savedStateHandle: SavedStateHandle
):ViewModel() {
    private val prefs = context.getSharedPreferences("meal_prefs", Context.MODE_PRIVATE)
    private val FASTING_START_KEY = "fasting_started_at"
    fun isPastNext3AM(startedAtMillis: Long): Boolean {
        val now = java.time.ZonedDateTime.now(java.time.ZoneId.of("Asia/Seoul"))
        val started = java.time.Instant.ofEpochMilli(startedAtMillis)
            .atZone(java.time.ZoneId.of("Asia/Seoul"))

        val next3AM = started.toLocalDate()
            .plusDays(if (started.hour >= 3) 1 else 0)
            .atTime(3, 0)
            .atZone(java.time.ZoneId.of("Asia/Seoul"))

        return now.isAfter(next3AM)
    }
    fun saveFastingStartTimeForType(mealType: String) {
        val key = "fasting_started_at_$mealType"
        prefs.edit().putLong(key, System.currentTimeMillis()).apply()
    }

    fun getFastingStartTimeForType(mealType: String): Long {
        val key = "fasting_started_at_$mealType"
        return prefs.getLong(key, -1L)
    }

    fun clearFastingStartTimeForType(mealType: String) {
        val key = "fasting_started_at_$mealType"
        prefs.edit().remove(key).apply()
    }

    fun isStillFastingForType(mealType: String): Boolean {
        val startedAt = getFastingStartTimeForType(mealType)
        return startedAt != -1L && !isPastNext3AM(startedAt)
    }
    private val _tempSearch = mutableStateOf<String?>(null)
    val tempSearch: State<String?> get() = _tempSearch
    fun getTempSearch(item: String){
        _tempSearch.value = ""
        _tempSearch.value = item
    }
    fun clearTempSearch(){
        _tempSearch.value = ""
    }
    private val _createMealState = mutableStateOf<Boolean?>(null)
    val createMealState: State<Boolean?> get() = _createMealState
    val formattedTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))

    private val _changeRecordSuccessState = mutableStateOf<Boolean?>(null)
    val changeRecordSuccessState: State<Boolean?> get() = _changeRecordSuccessState

    private val _selectType = mutableStateOf<String?>(null)
    val selectType: State<String?> get() = _selectType
    private val _createSimpleSuccessState = mutableStateOf<Boolean?>(null)
    val createSimpleSuccessState: State<Boolean?> get() = _createSimpleSuccessState

    private val _planCheck = mutableStateOf<Int?>(null)
    val planCheck: State<Int?> get() = _planCheck

    private val _selectedPlanMeals = mutableStateOf<List<MealResponseDto>>(emptyList())
    val selectedPlanMeals: State<List<MealResponseDto>> get() = _selectedPlanMeals
    fun resolveFoodsFromPlanType(dietType: String, onResolved: () -> Unit) {
        val plan = getPlanState.value ?: return
        val selected = plan.find { it.dietType == dietType } ?: return
        _dietId.value = selected.id

        viewModelScope.launch {
            val resolved = selected.dietFoods.mapNotNull { entry ->
                val food = foodRepository.getFood(entry.food.name)
                val quantity = entry.quantity.toFloat()
                if (food != null && quantity != null) {
                    FoodWithQuantity(food, quantity)
                } else null
            }
            _selectedFoods.clear()
            _selectedFoods.addAll(resolved)
            _planCheck.value = 1
            onResolved()
        }
    }
    fun createSimple(){
        val request = SimpleRequestDto(
            userId = 1,
            dietType = selectType.value?:""
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.createSimple(request)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _createSimpleSuccessState.value = true
                    Log.e("createSimple", "success")
                    val now = System.currentTimeMillis()
                    prefs.edit().putLong(FASTING_START_KEY, now).apply()
                }
                .onFailure {
                    _createSimpleSuccessState.value = false
                    Log.e("createSimple", it.message?: "Unknown error")
                }
        }
    }
    private val _createPlanState = mutableStateOf<Boolean?>(null)
    val createPlanState: State<Boolean?> get() = _createPlanState
    fun createPlan(){
        val foods = selectedFoods.map {
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val request = PlanRequestDto(
            userId = 1,
            dietType = selectType.value?:",",
            foods = foods,
            date = planDate.value?:"2025-01-01"
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.createPlan(request)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _createPlanState.value = true
                    Log.e("createPlan", "success")
                }
                .onFailure {
                    _createPlanState.value = false
                    Log.e("createPlan", it.message?: "Unknown error")
                }
        }
    }
    private val _getPlanSuccessState = mutableStateOf<Boolean?>(null)
    val getPlanSuccessState: State<Boolean?> get() = _getPlanSuccessState

    private val _getPlanState = mutableStateOf<List<PlanResponseDto>?>(null)
    val getPlanState: State<List<PlanResponseDto>?> get() = _getPlanState
    fun getPlan(userId: Int) {
        viewModelScope.launch {
            runCatching { mealRepository.getPlan(userId) }
                .onSuccess { response ->
                    if (response.isSuccessful) {
                        _getPlanState.value = response.body().orEmpty()
                        _getPlanSuccessState.value = true
                        _planCheck.value = 0
                        getRecord(userId)           // still load existing records here
                    } else {
                        _getPlanSuccessState.value = false
                    }
                }
                .onFailure { _getPlanSuccessState.value = false }
        }
    }
    private val _monthTags =
        mutableStateOf<Map<LocalDate, Set<PlanTagType>>>(emptyMap())
    val monthTags: State<Map<LocalDate, Set<PlanTagType>>> get() = _monthTags

    private fun toPlanTag(entryType: String): PlanTagType = when (entryType.trim()) {
        "술자리" -> PlanTagType.DRINKING
        "외식"   -> PlanTagType.EATING_OUT
        "기록", "AI추천" -> PlanTagType.AI_RECOMMEND
        else -> PlanTagType.NONE
    }
    private fun toLocalDate(ts: String?): LocalDate? =
        ts?.take(10)?.let { runCatching { LocalDate.parse(it) }.getOrNull() }
    private fun extractPlanDate(dto: PlanResponseDto): LocalDate? {
        val d = dto.dietDate
        if (d.isNotBlank()) return runCatching { LocalDate.parse(d) }.getOrNull()
        return toLocalDateKST(dto.createdAt)
    }

    private fun computeMonthTags(items: List<PlanResponseDto>): Map<LocalDate, Set<PlanTagType>> =
        items.groupBy { extractPlanDate(it) }
            .filterKeys { it != null }
            .mapKeys { it.key!! }
            .mapValues { (_, dayItems) ->
                chooseTopTag(dayItems.map { toPlanTag(it.dietEntryType) })
                    ?.let { setOf(it) } ?: emptySet()
            }

    private fun chooseTopTag(tags: List<PlanTagType>): PlanTagType? {
        val priority = listOf(
            PlanTagType.DRINKING,
            PlanTagType.EATING_OUT,
            PlanTagType.AI_RECOMMEND
        )
        return tags
            .filter { it != PlanTagType.NONE }
            .minByOrNull { t -> priority.indexOf(t).takeIf { it >= 0 } ?: Int.MAX_VALUE }
    }
    fun setPlanDate(date: LocalDate) {
        _planDate.value = date.toString()
    }
    private fun toLocalDateKST(ts: String?): LocalDate? = runCatching {
        java.time.OffsetDateTime.parse(ts).atZoneSameInstant(java.time.ZoneId.of("Asia/Seoul")).toLocalDate()
    }.getOrNull()

    private val _getMonthPlanSuccessState = mutableStateOf<Boolean?>(null)
    val getMonthPlanSuccessState: State<Boolean?> get() = _getMonthPlanSuccessState

    private val _getMonthPlanState = mutableStateOf<List<PlanResponseDto>?>(null)
    val getMonthPlanState: State<List<PlanResponseDto>?> get() = _getMonthPlanState
    fun getMonthPlan(userId: Int, yearMonth: String) {
        viewModelScope.launch {
            runCatching { mealRepository.getMonthPlan(userId, yearMonth) }
                .onSuccess { response ->
                    if (response.isSuccessful) {
                        val body = response.body().orEmpty()
                        _getMonthPlanState.value = body
                        _getMonthPlanSuccessState.value = true
                        _monthTags.value = computeMonthTags(body)
                    } else {
                        _getMonthPlanSuccessState.value = false
                    }
                }
                .onFailure { _getMonthPlanSuccessState.value = false }
        }
    }
    fun postAllUnrecordedPlans() {
        val plans   = _getPlanState.value.orEmpty()
        val records = _getRecordState.value.orEmpty().map { it.dietType }

        plans.forEach { planDto ->
            if (!records.contains(planDto.dietType)) {
                postMealFromPlan(planDto)
            }
        }
    }
    private fun postMealFromPlan(dto: PlanResponseDto) {
        val foods = dto.dietFoods.map { foodItem ->
            FoodRequestDto(
                foodId   = foodItem.food.id,
                quantity = foodItem.quantity.toFloat()
            )
        }

        val request = MealRequestDto(
            userId   = 1,
            dietType = dto.dietType,
            foods    = foods,
            name     = "${dto.dietType}식단",
            dietTime = "12:00:00"
        )

        viewModelScope.launch {
            runCatching {
                mealRepository.createMeal(request)
            }
                .onSuccess {
                    Log.d("AutoPost", "Posted plan for ${dto.dietType}")
                }
                .onFailure {
                    Log.e("AutoPost", "Failed for ${dto.dietType}", it)
                }
        }
    }
    private val _changePlanSuccessState = mutableStateOf<Boolean?>(null)
    val changePlanSuccessState: State<Boolean?> get() = _changePlanSuccessState
    fun changePlan(){
        val foods = selectedFoods.map {
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val request = PlanRequestDto(
            userId = 1,
            dietType = selectType.value?:"",
            foods = foods,
            date = planDate.value?:"2025-01-01"
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.changePlan(dietId = dietId.value?:1, request)
            }.onSuccess {
                Log.d("API", "Success")
                _changePlanSuccessState.value = true
                Log.e("changePlan", "success")
            }
                .onFailure {
                    _changePlanSuccessState.value = false
                    Log.e("changePlan", it.message?: "Unknown error")
                }
        }
    }


    fun editMeal(

    ){
        val foods = selectedFoods.map{
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val dietRequest = MealRequestDto(
            userId = 1,
            dietType = selectType.value?:"",
            foods = foods,
            name = selectType.value?:"" + "식단",
            dietTime = _selectedTime.value ?: formattedTime
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.changeRecord(dietId = dietId.value?:1,dietRequest)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _changeRecordSuccessState.value = true
                    Log.e("editDiet", "success")
                }
                .onFailure {
                    _changeRecordSuccessState.value = false
                    Log.e("editDiet", it.message?: "Unknown error")
                }
        }
    }

    fun createMeal(
    ){
        val foods = selectedFoods.map{
            FoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity
            )
        }
        val dietRequest = MealRequestDto(
            userId = 1,
            dietType = selectType.value?:"",
            foods = foods,
            name = selectType.value?:"" + "식단",
            dietTime = _selectedTime.value ?: formattedTime
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.createMeal(dietRequest)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _createMealState.value = true
                    Log.e("createDiet", "success")
                }
                .onFailure {
                    _createMealState.value = false
                    Log.e("creatDiet", it.message?: "Unknown error")
                }
        }
    }
    private val _changeSnackState = mutableStateOf<Boolean?>(null)
    val changeSnackState: State<Boolean?> get() = _changeSnackState
    fun changeSnack(){
        val foods = selectedFoods.map {
            SnackFoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity,
                dietTime = selectedTime.value?:""
            )
        }
        val request = SnackRequestDto(
            userId = 1,
            name = selectType.value + "식단",
            foods = foods
        )
        viewModelScope.launch {
            runCatching {
                mealRepository.changeSnack(dietId = dietId.value?:1,request)
            }
                .onSuccess {
                    Log.d("API", "Success")
                    _changeSnackState.value = true
                    Log.e("changeSnack", "success")
                }
                .onFailure {
                    _changeSnackState.value = false
                    Log.e("changeSnack", it.message?: "Unknown error")
                }
        }
    }
    private val _createSnackState = mutableStateOf<Boolean?>(null)
    val createSnackState: State<Boolean?> get() = _createSnackState
    fun createSnack(){
        val foods = selectedFoods.map {
            SnackFoodRequestDto(
                foodId = it.food.id,
                quantity = it.quantity,
                dietTime = selectedTime.value?:""
            )
        }
        val request = SnackRequestDto(
            userId = 1,
            name = selectType.value + "식단",
            foods = foods
        )
        viewModelScope.launch {
            runCatching {
            mealRepository.createSnack(request)
        }
                .onSuccess {
                    Log.d("API", "Success")
                    _createSnackState.value = true
                    Log.e("createSnack", "success")
                }
                .onFailure {
                    _createSnackState.value = false
                    Log.e("creatSnack", it.message?: "Unknown error")
                }
        }
    }
    private val _selectedFoods = mutableStateListOf<FoodWithQuantity>()
    val selectedFoods: List<FoodWithQuantity> get() = _selectedFoods

    private val _planDate = mutableStateOf<String?>(null)
    val planDate: State<String?> get() = _planDate

    init {
        val date = savedStateHandle.get<String>("date") ?: ""
        if(date.isNotEmpty()){
            _planDate.value = date
        }
    }

    init {
            val dietId = savedStateHandle.get<Int>("dietId") ?: -1
            val fwqRawString = savedStateHandle.get<String>("fwqRaw") ?: ""
            val mealType = savedStateHandle.get<String>("mealType") ?: ""

            if (mealType.isNotEmpty()) {
                setType(mealType)
            }

            Log.d("MealViewModel", "dietId=$dietId, fwqRawString=$fwqRawString")

            if (dietId != -1 && fwqRawString.isNotEmpty()) {
                val parsed = fwqRawString.split(",").mapNotNull {
                    val parts = it.split(":")
                    if (parts.size == 2) {
                        val name = parts[0]
                        val quantity =
                            parts[1].toFloatOrNull()?.toString() ?: return@mapNotNull null
                        name to quantity
                    } else null
                }

                Log.d("MealViewModel", "Calling getTemp with parsed fwqRaw=$parsed")
                getTemp(dietId, parsed)
            }
    }

    fun getTemp(dietId: Int, fwqRaw: List<Pair<String, String>>) {
        viewModelScope.launch {
            val resolved = fwqRaw.mapNotNull { (name, quantityStr) ->
                val clean = quantityStr.removeSuffix("g").trim()
                val food = foodRepository.getFood(name)
                val quantity = clean.toFloatOrNull()
                if (food != null && quantity != null) {
                    FoodWithQuantity(food, quantity)
                } else {
                    null
                }
            }
            _selectedFoods.addAll(resolved)
            _dietId.value = dietId
        }
    }
    fun addFood(food: Food, quantity: Float) {
        val existing = _selectedFoods.indexOfFirst { it.food.id == food.id }
        if (existing >= 0) {
            _selectedFoods[existing] = FoodWithQuantity(food, quantity)
        } else {
            _selectedFoods.add(FoodWithQuantity(food, quantity))
        }
    }

    private val _selectedTime = mutableStateOf<String?>(null)
    val selectedTime: State<String?> get() = _selectedTime

    fun setMealTime(hour: String, minute: String, isAM: Boolean) {
        val h = hour.toInt().let {
            if (isAM && it == 12) 0
            else if (!isAM && it != 12) it + 12
            else it
        }.toString().padStart(2, '0')

        val formatted = "$h:${minute.padStart(2, '0')}:00"
        _selectedTime.value = formatted
    }

    fun setType(type: String){
        _selectType.value = type
    }

    fun removeFood(item: FoodWithQuantity) {
        _selectedFoods.remove(item)
    }

    fun clearFoods() {
        _selectedFoods.clear()
    }

    private val _getRecordSuccessState = mutableStateOf<Boolean?>(null)
    val getRecordSuccessState: State<Boolean?> get() = _getRecordSuccessState

    private val _getRecordState = mutableStateOf<List<MealResponseDto>?>(null)
    val getRecordState: State<List<MealResponseDto>?> get() = _getRecordState

    fun getRecord(userId: Int) {
        viewModelScope.launch {
            runCatching {
                mealRepository.getRecord(userId)
            }.onSuccess { response ->
                if (response.isSuccessful) {
                    val body = response.body()
                    _getRecordState.value = body
                    _mealCardData.value = body
                        ?.filterNotNull()       // null 포인터 익셉션 방지
                        ?.map { parseToMealCardData(it) }
                        ?: emptyList()

//                    _mealCardData.value = body?.map { parseToMealCardData(it) } ?: emptyList()
                    _getRecordSuccessState.value = true
                } else {
                    _getRecordSuccessState.value = false
                    Log.e("getRecord", "Unsuccessful response: ${response.code()}")
                }
            }.onFailure {
                _getRecordSuccessState.value = false
                Log.e("getRecord", it.message ?: "Unknown error")
            }
        }
    }


    private val _mealCardData = mutableStateOf<List<MealCardData>>(emptyList())
    val mealCardData: State<List<MealCardData>> = _mealCardData
    fun parseToMealCardData(dto: MealResponseDto): MealCardData {
        val foodList = dto.dietFoods.map { foodItem ->
            Triple(
                foodItem.food.foodType.toDrawable(),
                foodItem.food.name,
                "${foodItem.quantity}g"
            )
        }

        val registeredHour = dto.dietFoods.firstOrNull()?.dietTime
            ?.split(":")?.getOrNull(0)
            ?.toIntOrNull()

        return MealCardData(
            dietId = dto.id,
            mealType = dto.dietType,
            totalKcal = "${dto.totalKcal}kcal",
            foodList = foodList,
            registeredHour = registeredHour
        )
    }
    fun parseToPlanMealCardData(dto: PlanResponseDto): MealCardData {
        val foodList = dto.dietFoods.map { foodItem ->
            Triple(
                foodItem.food.foodType.toDrawable(),
                foodItem.food.name,
                "${foodItem.quantity}g"
            )
        }

        val registeredHour = dto.dietFoods.firstOrNull()?.dietTime
            ?.split(":")?.getOrNull(0)
            ?.toIntOrNull()

        return MealCardData(
            dietId = dto.id,
            mealType = dto.dietType,
            totalKcal = "${dto.totalKcal}kcal",
            foodList = foodList,
            registeredHour = registeredHour
        )
    }

    val totalCarb: Double
        get() = selectedFoods.sumOf { it.food.carb * it.quantity }

    val totalProtein: Double
        get() = selectedFoods.sumOf { it.food.protein * it.quantity }

    val totalFat: Double
        get() = selectedFoods.sumOf { it.food.fat * it.quantity }

    private val _getChangeSuccessState = mutableStateOf<Boolean?>(null)
    val getChangeSuccessState: State<Boolean?> get() = _getChangeSuccessState

    private val _dietId = mutableStateOf<Int?>(null)
    val dietId: State<Int?> get() = _dietId
    fun addFoodsFromDiet(foodList: List<FoodWithQuantity>) {
        _selectedFoods.clear()
        _selectedFoods.addAll(foodList)
    }
    private val _aiDateSlotTags =
        mutableStateOf<Map<LocalDate, Map<DietType, PlanTagType>>>(emptyMap())
    val aiDateSlotTags: State<Map<LocalDate, Map<DietType, PlanTagType>>> get() = _aiDateSlotTags


    private fun recomputeAggregatesFromSlotTags() {
        val aggregatedTags: Map<LocalDate, Set<PlanTagType>> =
            _aiDateSlotTags.value.mapValues { (_, slotMap) -> slotMap.values.toSet() }
        _aiDate.value = aggregatedTags

        val aggregatedSlots: Map<LocalDate, Set<DietType>> =
            _aiDateSlotTags.value.mapValues { (_, slotMap) -> slotMap.keys.toSet() }
        _aiDietSlots.value = aggregatedSlots
    }
    fun setTagForSlots(date: LocalDate, slots: Set<DietType>, tag: PlanTagType) {
        val current = _aiDateSlotTags.value.toMutableMap()
        val perSlot = current[date]?.toMutableMap() ?: mutableMapOf()
        slots.forEach { slot -> perSlot[slot] = tag }
        current[date] = perSlot.toMap()
        _aiDateSlotTags.value = current.toMap()
        recomputeAggregatesFromSlotTags()
    }

    fun clearAllFor(date: LocalDate) {
        val current = _aiDateSlotTags.value.toMutableMap()
        current.remove(date)
        _aiDateSlotTags.value = current.toMap()
        recomputeAggregatesFromSlotTags()
    }
    private val _aiDietSlots = mutableStateOf<Map<LocalDate, Set<DietType>>>(emptyMap())
    val aiDietSlots: State<Map<LocalDate, Set<DietType>>> get() = _aiDietSlots

    fun setSlotsFor(date: LocalDate, slots: Set<DietType>) {
        _aiDietSlots.value = _aiDietSlots.value.toMutableMap().apply { put(date, slots) }
    }
    fun clearSlotsFor(date: LocalDate) {
        _aiDietSlots.value = _aiDietSlots.value.toMutableMap().apply { remove(date) }
    }
    private val _aiDate = mutableStateOf<Map<LocalDate, Set<PlanTagType>>>(emptyMap())
    val aiDate: State<Map<LocalDate, Set<PlanTagType>>> get() = _aiDate

    private val _postAiSuccessState = mutableStateOf<Boolean?>(null)
    val postAiSuccessState: State<Boolean?> get() = _postAiSuccessState

    fun setTagsFor(date: LocalDate, tags: Set<PlanTagType>) {
        _aiDate.value = _aiDate.value.toMutableMap().apply { put(date, tags) }
    }
    fun clearTagsFor(date: LocalDate) {
        _aiDate.value = _aiDate.value.toMutableMap().apply { remove(date) }
    }
    private fun PlanTagType.toApiLabel(): String = when (this) {
        PlanTagType.NONE         -> "없음"
        PlanTagType.EATING_OUT   -> "외식"
        PlanTagType.DRINKING     -> "술자리"
        PlanTagType.AI_RECOMMEND -> "AI추천"
    }
    private fun pickSingleTag(tags: Set<PlanTagType>): PlanTagType =
        tags.firstOrNull { it != PlanTagType.NONE } ?: PlanTagType.NONE

    private val _planDietType = mutableStateOf<String?>(null)
    val planDietType: State<String?> get() = _planDietType

    fun postAi() {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE
        val activities: List<AiDto> = _aiDateSlotTags.value.flatMap { (date, perSlot) ->
            perSlot.map { (slot, tag) ->
                AiDto(
                    dietDate = date.format(formatter),
                    dietEntryType = when (tag) {
                        PlanTagType.NONE         -> "없음"
                        PlanTagType.EATING_OUT   -> "외식"
                        PlanTagType.DRINKING     -> "술자리"
                        PlanTagType.AI_RECOMMEND -> "AI추천"
                    },
                    dietType = slot.toApiLabel()
                )
            }
        }

        viewModelScope.launch {
            runCatching {
                mealRepository.postAi(
                    AiRequestDto(userId = 1, dietActivities = activities)
                )
            }.onSuccess {
                Log.d("API", "Success")
                _postAiSuccessState.value = true
            }.onFailure {
                _postAiSuccessState.value = false
                Log.e("postAi", it.message ?: "Unknown error")
            }
        }
    }
}

data class FoodWithQuantity(
    val food: Food,
    val quantity: Float
)

data class MealCardData(
    val mealType: String,
    val totalKcal: String,
    val dietId: Int,
    val foodList: List<Triple<Int, String, String>>,
    val registeredHour: Int? = null
)

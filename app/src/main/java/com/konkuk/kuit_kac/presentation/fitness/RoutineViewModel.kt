package com.konkuk.kuit_kac.presentation.fitness

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoutineViewModel @Inject constructor(
    private val routineRepository: RoutineRepository
): ViewModel() {
    private val _createRoutineState = mutableStateOf<Boolean?>(null)
    val createRoutineState: State<Boolean?> get() = _createRoutineState

    fun createRoutine(

    ){

    }


}
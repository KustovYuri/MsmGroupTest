package com.example.msmgrouptest.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msmgrouptest.domain.models.SingInResponse
import com.example.msmgrouptest.domain.use_cases.GetUserDataUseCase
import com.example.msmgrouptest.domain.use_cases.UpdateUserDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase
): ViewModel() {

    private var timerJob: Job? = null
    private val _timeRemains = mutableFloatStateOf(1f)
    val timeRemains: State<Float> = _timeRemains

    private val _userData = mutableStateOf(UserDataState.Success(getUserDataUseCase()))
    val userData: State<UserDataState> = _userData

    init {
        startTimer()
    }

    private fun startTimer(){
        val totalTime: Long = 60L * 1000L
        var currentTime: Long = 60L * 1000L

        timerJob = viewModelScope.launch {
            while (currentTime.toFloat() > 0f){
                delay(100L)
                currentTime -= 100L
                _timeRemains.floatValue = currentTime.toFloat() / totalTime
            }
        }
    }

}
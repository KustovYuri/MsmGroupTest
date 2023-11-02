package com.example.msmgrouptest.ui.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msmgrouptest.domain.models.SingInResponse
import com.example.msmgrouptest.domain.use_cases.DataStoreUseCase
import com.example.msmgrouptest.domain.use_cases.GetUserDataUseCase
import com.example.msmgrouptest.domain.use_cases.UpdateUserDataUseCase
import com.example.msmgrouptest.ui.navigation.Navigation
import com.example.msmgrouptest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    getUserDataUseCase: GetUserDataUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    private var timerJob: Job? = null
    private var updateDataJob: Job? = null

    private val _timeRemains = mutableFloatStateOf(1f)
    val timeRemains: State<Float> = _timeRemains

    private val _userData = mutableStateOf(
        if(getUserDataUseCase() != null)
            UserDataState.Success(getUserDataUseCase()!!)
        else UserDataState.Error
    )

    val userData: State<UserDataState> = _userData

    init {
        startTimer()
    }

    fun exitFromAccount(navigationRoute: () -> Unit){
        viewModelScope.launch {
            dataStoreUseCase.setCredentials(null)
            navigationRoute()
        }
    }

    private fun startTimer(){
        val totalTime: Long = 5L * 1000L
        var currentTime: Long = totalTime

        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            while (currentTime.toFloat() > 0f){
                delay(100L)
                currentTime -= 100L
                _timeRemains.floatValue = currentTime.toFloat() / totalTime
            }
            updateData()
        }
    }

    private fun updateData(){

        updateDataJob?.cancel()

        updateDataJob = viewModelScope.launch {
            updateUserDataUseCase().onEach { result->
                Log.d("myMainActivity", "------------ new request")
                when(result){
                    is Resource.Error -> updateData()
                    is Resource.Loading -> _userData.value = UserDataState.Loading
                    is Resource.Success -> {
                        _userData.value = UserDataState.Success(result.data!!)
                        startTimer()
                    }
                }
            }.launchIn(this)
        }

    }

}
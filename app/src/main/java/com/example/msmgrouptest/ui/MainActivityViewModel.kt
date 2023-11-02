package com.example.msmgrouptest.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msmgrouptest.domain.use_cases.DataStoreUseCase
import com.example.msmgrouptest.ui.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    private val _startScreen = mutableStateOf<Navigation?>(null)
    val startScreen: State<Navigation?> = _startScreen

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    val disconnect = mutableStateOf(false)

    init {
        getStartNavigationScreen()
    }

    private fun getStartNavigationScreen(){
        viewModelScope.launch {
            val data: Deferred<Navigation> = async{
                when(dataStoreUseCase.userIsAuthorize()){
                    true -> Navigation.MainScreen
                    false -> Navigation.InitializationScreen
                }
            }
            _startScreen.value = data.await()
            delay(200)
            _isLoading.value = false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateSleepData(){
        viewModelScope.launch {
            val currentTime = LocalTime.now()
            dataStoreUseCase.setSleepData(currentTime.toString())
            Log.d("myMainActivity", "success time ${currentTime}")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun equalsSleepData(){
        viewModelScope.launch{
            if (dataStoreUseCase.getSleepData() != null){
                val timer = 1
                val currentTime = LocalTime.now()
                val sleepTime = LocalTime.parse(dataStoreUseCase.getSleepData())
                Log.d("myMainActivity", "equalsTime ${sleepTime.until(currentTime, ChronoUnit.MINUTES)}")
                if (sleepTime != null){
                    if(sleepTime.until(currentTime, ChronoUnit.MINUTES).toInt() >= timer){
                        Log.d("myMainActivity", "request ${true}")
                        dataStoreUseCase.setCredentials(null)
                        disconnect.value = true
                    }
                    else{
                        dataStoreUseCase.setSleepData(null)
                    }
                }
            }
        }


    }

}
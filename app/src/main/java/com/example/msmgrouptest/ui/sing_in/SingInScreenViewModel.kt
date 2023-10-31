package com.example.msmgrouptest.ui.sing_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.use_cases.SingInUseCase
import com.example.msmgrouptest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingInScreenViewModel @Inject constructor(
    private val sigInUseCase: SingInUseCase
): ViewModel() {
    private val _inputUserData = mutableStateOf(SingInDataModel("", ""))
    val inputUserData: State<SingInDataModel> = _inputUserData

    private val _isLoadingData = mutableStateOf(false)
    val isLoadingData: State<Boolean> = _isLoadingData

    private val authEventChannel = Channel<SingInEvent>()
    val authEvents = authEventChannel.receiveAsFlow()

    fun setLoginData(text:String){
        _inputUserData.value = _inputUserData.value.copy(login = text)
    }

    fun setPasswordData(text:String){
        _inputUserData.value = _inputUserData.value.copy(password = text)
    }

    private var flowJob: Job? = null

    fun sendData(){

        flowJob?.cancel()

        flowJob = viewModelScope.launch {
            sigInUseCase(_inputUserData.value).onEach { result ->

                _isLoadingData.value = false

                when(result){
                    is Resource.Success->{
                        authEventChannel.send(SingInEvent.Success(result.data?.userName?:"Успех"))
                    }
                    is Resource.Error->{
                        authEventChannel.send(SingInEvent.Error(result.message?:"Неизвестная ошибка"))
                    }
                    is Resource.Loading->{
                        _isLoadingData.value = true
                    }
                }
            }.launchIn(this)
        }
    }

    sealed class SingInEvent{
        data class Success(val message:String): SingInEvent()
        data class Error(val message: String) : SingInEvent()
    }
}
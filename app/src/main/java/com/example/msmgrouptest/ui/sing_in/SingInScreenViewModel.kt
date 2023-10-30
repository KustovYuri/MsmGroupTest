package com.example.msmgrouptest.ui.sing_in

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.use_cases.SingInUseCase
import com.example.msmgrouptest.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SingInScreenViewModel @Inject constructor(
    private val sigInUseCase: SingInUseCase
): ViewModel() {
    private val _inputUserData = mutableStateOf(SingInDataModel("", ""))
    val inputUserData: State<SingInDataModel> = _inputUserData

    private val _isLoadingData = mutableStateOf(false)
    val isLoadingData: State<Boolean> = _isLoadingData

    fun setLoginData(text:String){
        _inputUserData.value = _inputUserData.value.copy(login = text)
    }

    fun setPasswordData(text:String){
        _inputUserData.value = _inputUserData.value.copy(password = text)
    }

    fun sendData(){
        sigInUseCase(_inputUserData.value).onEach { result ->

            _isLoadingData.value = false

            when(result){
                is Resource.Success->{

                }
                is Resource.Error->{

                }
                is Resource.Loading->{
                    _isLoadingData.value = true
                }
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}
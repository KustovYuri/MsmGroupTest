package com.example.msmgrouptest.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msmgrouptest.domain.use_cases.DataStoreUseCase
import com.example.msmgrouptest.ui.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {

    private val _startScreen = mutableStateOf<Navigation?>(null)
    val startScreen: State<Navigation?> = _startScreen

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
        }
    }

}
package com.example.msmgrouptest.ui.host_settings_dialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.msmgrouptest.domain.use_cases.GetBaseUrlUseCase
import com.example.msmgrouptest.domain.use_cases.GetStartPathUseCase
import com.example.msmgrouptest.domain.use_cases.SetBaseUrlUseCase
import com.example.msmgrouptest.domain.use_cases.SetStartPathUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HostSettingsDialogViewModel @Inject constructor(
    private val setBaseUrlUseCase: SetBaseUrlUseCase,
    private val getBaseUrlUseCase: GetBaseUrlUseCase,
    private val setStartPathUseCase: SetStartPathUseCase,
    private val getStartPathUseCase: GetStartPathUseCase,
):ViewModel() {
    private val _hostsName = mutableStateOf(
        listOf("https://test.wlbs.ru/New/", "https://test1.wlbs.ru/new/", "https://test2.wlbs.ru/old/", "https://test3.wlbs.ru/Old/", "https://test4.wlbs.ru/New/")
    )
    val hostName: State<List<String>> = _hostsName

    private val _selectedHost = mutableStateOf(getBaseUrlUseCase()+getStartPathUseCase())
    val selectedHost:State<String> = _selectedHost

    fun updateSelectedHost(){
        _selectedHost.value = getBaseUrlUseCase()+getStartPathUseCase()
    }

    fun setSelectedHost(host:String){
        _selectedHost.value = host
    }

    fun submitChanged(){
        val paths = _selectedHost.value.split("/")
        setBaseUrlUseCase(paths[0]+"//"+paths[2]+"/")
        setStartPathUseCase(paths[3]+"/")
    }
}
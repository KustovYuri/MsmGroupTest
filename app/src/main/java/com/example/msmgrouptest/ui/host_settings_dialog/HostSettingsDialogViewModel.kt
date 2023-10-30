package com.example.msmgrouptest.ui.host_settings_dialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HostSettingsDialogViewModel @Inject constructor(

):ViewModel() {
    private val _hostsName = mutableStateOf(
        listOf("fefufit.dvfu.ru", "fefufit.vgues.ru", "fefufit.mgu.ru", "fefufit.sharaga.ru",)
    )
    val hostName: State<List<String>> = _hostsName

    private val _selectedHost = mutableStateOf("fefufit.dvfu.ru")
    val selectedHost:State<String> = _selectedHost

    fun setSelectedHost(host:String){
        _selectedHost.value = host
    }
}
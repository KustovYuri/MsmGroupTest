package com.example.msmgrouptest.ui.host_settings_dialog

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.msmgrouptest.Config
import com.example.msmgrouptest.di.NetworkModule
import com.example.msmgrouptest.domain.use_cases.SetBaseUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.internal.DaggerCollections
import dagger.internal.DaggerGenerated
import javax.inject.Inject

@HiltViewModel
class HostSettingsDialogViewModel @Inject constructor(
    private val setBaseUrlUseCase: SetBaseUrlUseCase
):ViewModel() {
    private val _hostsName = mutableStateOf(
        listOf("test.wlbs.ru", "fefufit.dvfu.ru", "fefufit.vgues.ru", "fefufit.mgu.ru", "fefufit.sharaga.ru",)
    )
    val hostName: State<List<String>> = _hostsName

    private val _selectedHost = mutableStateOf("fefufit.dvfu.ru")
    val selectedHost:State<String> = _selectedHost

    fun setSelectedHost(host:String){
        _selectedHost.value = host
        setBaseUrlUseCase("https://$host")
    }
}
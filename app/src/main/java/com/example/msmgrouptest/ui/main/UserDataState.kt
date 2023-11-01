package com.example.msmgrouptest.ui.main

import com.example.msmgrouptest.domain.models.SingInResponse

sealed class UserDataState {
    data class Success(val userData: SingInResponse): UserDataState()

    object Loading: UserDataState()

    object Error: UserDataState()
}
package com.example.msmgrouptest.domain.repositories

import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse

interface InitializationRepository {
    suspend fun singIn(singInData: SingInDataModel): SingInResponse

    suspend fun updateUserData(): SingInResponse

    fun getUserData(): SingInResponse
}
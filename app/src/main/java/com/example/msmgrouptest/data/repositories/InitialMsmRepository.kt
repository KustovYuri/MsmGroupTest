package com.example.msmgrouptest.data.repositories

import com.example.msmgrouptest.data.data_sources.MsmApi
import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse
import com.example.msmgrouptest.domain.repositories.InitializationRepository
import javax.inject.Inject

class InitialMsmRepository @Inject constructor(
    private val msmApi: MsmApi,
) : InitializationRepository {
    override suspend fun singIn(singInData: SingInDataModel): SingInResponse {
           return msmApi.singIn(singInData)
    }
}
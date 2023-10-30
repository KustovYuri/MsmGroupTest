package com.example.msmgrouptest.ui.data.repositories

import com.example.msmgrouptest.ui.data.data_sources.MsmApi
import com.example.msmgrouptest.ui.domain.models.DataSingInDataModel
import com.example.msmgrouptest.ui.domain.models.DataSingInResponse
import com.example.msmgrouptest.ui.domain.repositories.InitializationRepository
import javax.inject.Inject

class InitialMsmRepository @Inject constructor(
    private val msmApi: MsmApi,
) : InitializationRepository {
    override suspend fun singIn(singInData: DataSingInDataModel): DataSingInResponse {
           return msmApi.singIn(singInData)
    }
}
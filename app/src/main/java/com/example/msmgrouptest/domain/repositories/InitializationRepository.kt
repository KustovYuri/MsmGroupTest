package com.example.msmgrouptest.ui.domain.repositories

import com.example.msmgrouptest.ui.domain.models.DataSingInDataModel
import com.example.msmgrouptest.ui.domain.models.DataSingInResponse

interface InitializationRepository {
    suspend fun singIn(singInData: DataSingInDataModel): DataSingInResponse
}
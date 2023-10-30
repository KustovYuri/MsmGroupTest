package com.example.msmgrouptest.ui.data.data_sources

import com.example.msmgrouptest.ui.domain.models.DataSingInDataModel
import com.example.msmgrouptest.ui.domain.models.DataSingInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MsmApi {
    @POST("/api2/auth/login")
    suspend fun singIn(@Body singInData: DataSingInDataModel): DataSingInResponse
}
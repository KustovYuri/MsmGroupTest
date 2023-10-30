package com.example.msmgrouptest.data.data_sources

import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface MsmApi {
    @POST("/api2/auth/login")
    suspend fun singIn(@Body singInData: SingInDataModel): SingInResponse
}
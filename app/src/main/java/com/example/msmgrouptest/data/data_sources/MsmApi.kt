package com.example.msmgrouptest.data.data_sources

import android.util.Base64
import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface MsmApi {
    @POST("/New/hs/MobileAgent/authorization/")
    suspend fun singIn(@Header("Authorization") authData: String):SingInResponse
}
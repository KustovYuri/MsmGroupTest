package com.example.msmgrouptest.data.data_sources

import com.example.msmgrouptest.domain.models.SingInResponse
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface MsmApi {
    @POST("{startPath}/hs/MobileAgent/authorization/")
    suspend fun singIn(@Path("startPath", encoded = true) startPath: String, @Header("Authorization") authData: String):SingInResponse
}
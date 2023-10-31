package com.example.msmgrouptest.data.repositories

import android.util.Base64
import com.example.msmgrouptest.data.data_sources.MsmApi
import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse
import com.example.msmgrouptest.domain.repositories.InitializationRepository
import okhttp3.Credentials
import javax.inject.Inject

class InitialMsmRepository @Inject constructor(
    private val msmApi: MsmApi,
) : InitializationRepository {
    override suspend fun singIn(singInData: SingInDataModel): SingInResponse {
        val credentials = Credentials.basic(singInData.login, singInData.password)
        return msmApi.singIn(credentials)
    }
}
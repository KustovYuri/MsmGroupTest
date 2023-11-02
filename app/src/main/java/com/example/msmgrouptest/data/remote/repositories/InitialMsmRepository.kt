package com.example.msmgrouptest.data.remote.repositories

import android.util.Base64
import com.example.msmgrouptest.data.remote.data_sources.MsmApi
import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse
import com.example.msmgrouptest.domain.repositories.InitializationRepository
import com.example.msmgrouptest.domain.use_cases.GetStartPathUseCase
import okhttp3.Credentials
import javax.inject.Inject

class InitialMsmRepository @Inject constructor(
    private val msmApi: MsmApi,
    private val getStartPathUseCase: GetStartPathUseCase
) : InitializationRepository {

    private var userData: SingInResponse? = null
    private var userCredentials: String? = null
    override suspend fun singIn(singInData: SingInDataModel): SingInResponse {
        val credentials = convertToBasic(singInData)
        return request(credentials)
    }

    override fun getUserData(): SingInResponse? {
        return userData
    }

    override suspend fun updateUserData(): SingInResponse {
        return request(userCredentials!!)
    }

    private fun convertToBasic(singInData: SingInDataModel):String{
        return Credentials.basic(singInData.login, singInData.password)
    }

    private suspend fun request(credentials: String):SingInResponse{
        val response = msmApi.singIn(getStartPathUseCase(),credentials)
        userCredentials = credentials
        userData = response

        return  response
    }
}
package com.example.msmgrouptest.domain.use_cases

import android.credentials.Credential
import com.example.data_store.entities.SleepTimeData
import com.example.data_store.entities.UserMetaData
import com.example.msmgrouptest.domain.repositories.DataStoreRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStoreUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {

    suspend fun userIsAuthorize():Boolean{
        val data = dataStoreRepository.getUserMetaData().first()

        return !data.userMetaData.credentials.isNullOrEmpty()
    }

    suspend fun getCredentials(): String{
        val data = dataStoreRepository.getUserMetaData().first()

        return data.userMetaData.credentials?:""
    }

    suspend fun getSleepData(): String?{
        val data = dataStoreRepository.getUserMetaData().first()

        return data.sleepTime.sleepTime
    }

    suspend fun setCredentials(credential: String?){
        dataStoreRepository.setUserMetaData(UserMetaData(credential))
    }

    suspend fun setSleepData(sleepData: String?){
        dataStoreRepository.setSleepTimeMetaData(SleepTimeData(sleepData))
    }

}
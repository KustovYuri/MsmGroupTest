package com.example.msmgrouptest.domain.repositories

import com.example.data_store.entities.AppInternalData
import com.example.data_store.entities.UserMetaData
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    fun getUserMetaData(): Flow<AppInternalData>

    suspend fun setUserMetaData(userMetaData: UserMetaData)
}
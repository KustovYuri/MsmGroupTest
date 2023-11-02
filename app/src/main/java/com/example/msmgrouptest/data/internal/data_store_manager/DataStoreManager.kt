package com.example.data_store.data_store_manager

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.data_store.entities.AppInternalData
import com.example.data_store.entities.UserMetaData


class DataStoreManager(
    private val appContext:Context,
    private val serializer: Serializer<AppInternalData>
) {
    private val Context.dataStore by dataStore("app-internal.json", serializer)
    private val appInternalDataStore = appContext.dataStore

    val data = appInternalDataStore.data

    suspend fun setUserMetaData(userMetaData: UserMetaData){
        appInternalDataStore.updateData {
            it.copy(userMetaData = userMetaData)
        }
    }

}
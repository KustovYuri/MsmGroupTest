package com.example.data_store.entities

import kotlinx.serialization.Serializable

@Serializable
data class AppInternalData(
    val userMetaData: UserMetaData = UserMetaData(),
    val sleepTime: SleepTimeData = SleepTimeData()
)

@Serializable
data class UserMetaData(
    val credentials:String? = null
)

@Serializable
data class SleepTimeData(
    val sleepTime: String? = null
)

package com.example.data_store.entities

import kotlinx.serialization.Serializable

@Serializable
data class AppInternalData(
    val userMetaData: UserMetaData = UserMetaData()
)

@Serializable
data class UserMetaData(
    val credentials:String? = null
)

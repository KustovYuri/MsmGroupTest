package com.example.msmgrouptest.domain.models

import com.google.gson.annotations.SerializedName

data class SingInDataModel(
    @SerializedName("email")
    val login: String,
    @SerializedName("password")
    val password: String
)

package com.example.msmgrouptest.ui.domain.models

import com.google.gson.annotations.SerializedName

data class DataSingInDataModel(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)

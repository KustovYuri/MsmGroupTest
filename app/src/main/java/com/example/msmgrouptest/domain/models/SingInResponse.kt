package com.example.msmgrouptest.domain.models


import com.google.gson.annotations.SerializedName

data class SingInResponse(
    @SerializedName("division_id")
    val divisionId: String,
    @SerializedName("division_name")
    val divisionName: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("user_name")
    val userName: String,
    @SerializedName("version")
    val version: Int
)
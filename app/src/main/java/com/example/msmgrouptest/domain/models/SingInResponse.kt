package com.example.msmgrouptest.domain.models

import com.google.gson.annotations.SerializedName

data class SingInResponse(
    @SerializedName("data")
    val data: DataUserMeta,
    @SerializedName("status")
    val status: String
)

data class DataUserMeta(
    @SerializedName("qr_token")
    val qrToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("token")
    val token: String,
    @SerializedName("type")
    val type: String
)

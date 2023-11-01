package com.example.msmgrouptest.data.utils

import android.net.Uri

class BaseUrlManager {
    private var baseUrl: String = "https://test.wlbs.ru/"

    fun setBaseUrl(baseUrl: String) {
        this.baseUrl = baseUrl
    }

    fun getBaseUrl(): String {
        return baseUrl
    }

    fun getHost(): String {
        return Uri.parse(baseUrl).host ?: ""
    }
}
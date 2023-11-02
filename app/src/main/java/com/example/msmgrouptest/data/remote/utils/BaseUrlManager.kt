package com.example.msmgrouptest.data.remote.utils

import android.net.Uri

class BaseUrlManager {
    private var baseUrl: String = "https://test.wlbs.ru/"
    private var startPath: String = "New/"

    fun setBaseUrl(baseUrl: String) {
        this.baseUrl = baseUrl
    }

    fun setStartPath(startPath: String) {
        this.startPath = startPath
    }

    fun getBaseUrl(): String {
        return baseUrl
    }

    fun getStartPath():String {
        return startPath
    }

    fun getHost(): String {
        return Uri.parse(baseUrl).host ?: ""
    }
}
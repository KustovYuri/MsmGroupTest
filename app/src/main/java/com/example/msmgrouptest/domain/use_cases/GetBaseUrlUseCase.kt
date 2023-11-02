package com.example.msmgrouptest.domain.use_cases

import com.example.msmgrouptest.data.remote.utils.BaseUrlManager
import javax.inject.Inject

class GetBaseUrlUseCase @Inject constructor(
    private val urlManager: BaseUrlManager
) {
    operator fun invoke():String{
        return urlManager.getBaseUrl()
    }
}
package com.example.msmgrouptest.domain.use_cases

import com.example.msmgrouptest.data.utils.BaseUrlManager
import javax.inject.Inject

class SetBaseUrlUseCase @Inject constructor(
    private val urlManager: BaseUrlManager
) {
    operator fun invoke(newUrl:String){
        urlManager.setBaseUrl(newUrl)
    }
}
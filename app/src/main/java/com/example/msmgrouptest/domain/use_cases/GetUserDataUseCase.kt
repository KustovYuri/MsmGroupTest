package com.example.msmgrouptest.domain.use_cases

import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse
import com.example.msmgrouptest.domain.repositories.InitializationRepository
import com.example.msmgrouptest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(
    private val singInRepository: InitializationRepository,
) {
    operator fun invoke(): SingInResponse? {
        return singInRepository.getUserData()
    }
}
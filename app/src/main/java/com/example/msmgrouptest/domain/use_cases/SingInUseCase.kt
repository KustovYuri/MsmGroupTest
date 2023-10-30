package com.example.msmgrouptest.domain.use_cases

import com.example.msmgrouptest.domain.models.SingInDataModel
import com.example.msmgrouptest.domain.models.SingInResponse
import com.example.msmgrouptest.domain.repositories.InitializationRepository
import com.example.msmgrouptest.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class SingInUseCase @Inject constructor(
    private val singInRepository: InitializationRepository,
) {
    operator fun invoke(singInData: SingInDataModel): Flow<Resource<SingInResponse>> = flow {
        try {
            emit(Resource.Loading())
            val response = singInRepository.singIn(singInData)
            emit(Resource.Success(response))
        }catch (e:Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}
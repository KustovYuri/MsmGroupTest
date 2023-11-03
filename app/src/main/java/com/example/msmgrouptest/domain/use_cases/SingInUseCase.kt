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
            println(e.message.toString())
            when(e.message.toString()){
                "timeout" -> emit(Resource.Error(message = "Превышено время ожидания сервера. Повторите попытку."))
                "Use JsonReader.setLenient(true) to accept malformed JSON at line 1 column 1 path \$" ->
                    emit(Resource.Error("Не удалость связаться с хостом, повторите попытку или смените хост в настройках."))
                "HTTP 401 Unauthorized" -> emit(Resource.Error("Неверный логин или пароль."))
                else -> emit(Resource.Error(e.message.toString()))
            }
        }
    }
}
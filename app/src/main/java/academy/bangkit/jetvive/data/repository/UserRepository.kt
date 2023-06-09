package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.source.local.datastore.UserPreferences
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.data.source.remote.response.RegisterResponse
import academy.bangkit.jetvive.data.source.remote.retrofit.ApiResponse
import academy.bangkit.jetvive.data.source.remote.retrofit.ApiService
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
    ) {

    fun register(registerRequest: RegisterRequest): Flow<ApiResponse<RegisterResponse>> = flow {
        emit(ApiResponse.Empty)
        try {
            val response = apiService.register(registerRequest)
            emit(ApiResponse.Success(response))
        } catch (exception: Exception) {
            Log.d("UserRepository", "register: ${exception.message.toString()}")
            emit(ApiResponse.Error(exception.message.toString()))
        }
    }

    suspend fun setLogin(userEntity: UserEntity) = userPreferences.setLogin(userEntity)
    fun getLogin(): Flow<UserEntity> = userPreferences.getLogin()
    suspend fun deleteLogin() = userPreferences.deleteLogin()

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(apiService: ApiService, userPreferences: UserPreferences): UserRepository =
            instance ?: synchronized(this) {
                UserRepository(apiService, userPreferences).apply {
                    instance = this
                }
            }
    }
}
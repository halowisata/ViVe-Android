package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.source.local.datastore.UserPreferences
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.data.source.remote.response.RegisterResponse
import academy.bangkit.jetvive.data.source.remote.retrofit.ApiService
import academy.bangkit.jetvive.ui.common.UiState
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
    ) {

    suspend fun register(registerRequest: RegisterRequest): UiState<RegisterResponse> {
        return try {
            val response = apiService.register(registerRequest)
            if (response.isSuccessful) {
                UiState.Success(response.body() ?: throw Exception("Empty response body"))
            } else {
                UiState.Error("Registration failed")
            }
        } catch (exception: Exception) {
            UiState.Error(exception.message.toString())
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
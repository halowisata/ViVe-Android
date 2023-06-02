package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.source.local.datastore.UserPreferences
import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userPreferences: UserPreferences) {

    suspend fun setLogin(userEntity: UserEntity) = userPreferences.setLogin(userEntity)
    fun getLogin(): Flow<UserEntity> = userPreferences.getLogin()
    suspend fun deleteLogin() = userPreferences.deleteLogin()

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userPreferences: UserPreferences): UserRepository =
            instance ?: synchronized(this) {
                UserRepository(userPreferences).apply {
                    instance = this
                }
            }
    }
}
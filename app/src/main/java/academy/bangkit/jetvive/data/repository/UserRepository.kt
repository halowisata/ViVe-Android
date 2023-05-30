package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.local.datastore.UserPreferences
import academy.bangkit.jetvive.data.local.entity.UserEntity
import academy.bangkit.jetvive.model.user.FakeUserDataSource
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userPreferences: UserPreferences) {

    suspend fun login(email: String, password: String): Boolean {
        var loginState = false

        FakeUserDataSource.dummyUsers.forEach { dummyUser ->
            if (email == dummyUser.email && password == dummyUser.password) {
                userPreferences.setLogin(UserEntity(
                    dummyUser.id,
                    dummyUser.name,
                    "dummyToken")
                )
                loginState = true
            }
        }

        return if (loginState) {
            loginState
        } else {
            loginState
        }
    }

    fun getLogin(): Flow<UserEntity> = userPreferences.getLogin()

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
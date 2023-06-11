package academy.bangkit.jetvive.data.source.local.datastore

import academy.bangkit.jetvive.data.source.local.entity.UserEntity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>){

    suspend fun setLogin(userEntity: UserEntity) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = userEntity.accessToken
            preferences[REFRESH_TOKEN] = userEntity.refreshToken
        }
    }

    fun getLogin(): Flow<UserEntity> = dataStore.data.map { preferences ->
        UserEntity(
            preferences[ACCESS_TOKEN] ?: "",
            preferences[REFRESH_TOKEN] ?: ""
        )
    }

    suspend fun deleteLogin() { dataStore.edit { it.clear() } }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val ACCESS_TOKEN = stringPreferencesKey("accessToken")
        private val REFRESH_TOKEN = stringPreferencesKey("refreshToken")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences =
            INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
    }
}
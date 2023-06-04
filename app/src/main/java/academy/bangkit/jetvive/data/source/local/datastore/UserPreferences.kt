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
            preferences[ID] = userEntity.id
            preferences[NAME] = userEntity.name
            preferences[TOKEN] = userEntity.token
        }
    }

    fun getLogin(): Flow<UserEntity> = dataStore.data.map { preferences ->
        UserEntity(
            preferences[ID] ?: "",
            preferences[NAME] ?: "",
            preferences[TOKEN] ?: ""
        )
    }

    suspend fun deleteLogin() { dataStore.edit { preferences -> preferences.clear() } }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        private val ID = stringPreferencesKey("id")
        private val NAME = stringPreferencesKey("name")
        private val TOKEN = stringPreferencesKey("token")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences =
            INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
    }
}
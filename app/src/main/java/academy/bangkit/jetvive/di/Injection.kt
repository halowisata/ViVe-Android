package academy.bangkit.jetvive.di

import academy.bangkit.jetvive.data.local.datastore.UserPreferences
import academy.bangkit.jetvive.data.repository.MoodRepository
import academy.bangkit.jetvive.data.repository.OnboardingRepository
import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore


object Injection {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        name = "user_preferences"
    )

    fun provideOnboardingRepository(): OnboardingRepository {
        return OnboardingRepository.getInstance()
    }

    private fun provideUserPreferences(context: Context): UserPreferences {
        val dataStore = context.dataStore

        return UserPreferences.getInstance(dataStore)
    }

    fun provideUserRepository(context: Context): UserRepository {
        return UserRepository.getInstance(provideUserPreferences(context))
    }

    fun provideMoodRepository(): MoodRepository {
        return MoodRepository.getInstance()
    }

    fun provideTouristAttractionRepository(): TouristAttractionRepository {
        return TouristAttractionRepository.getInstance()
    }
}
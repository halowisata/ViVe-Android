package academy.bangkit.jetvive.di

import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository


object Injection {
    fun provideUserRepository(): UserRepository {
        return UserRepository.getInstance()
    }

    fun provideTouristAttractionRepository(): TouristAttractionRepository {
        return TouristAttractionRepository.getInstance()
    }
}
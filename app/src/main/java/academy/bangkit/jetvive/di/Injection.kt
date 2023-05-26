package academy.bangkit.jetvive.di

import academy.bangkit.jetvive.data.repository.OnboardingRepository
import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository


object Injection {
    fun provideOnboardingRepository(): OnboardingRepository {
        return OnboardingRepository.getInstance()
    }

    fun provideUserRepository(): UserRepository {
        return UserRepository.getInstance()
    }

    fun provideTouristAttractionRepository(): TouristAttractionRepository {
        return TouristAttractionRepository.getInstance()
    }
}
package academy.bangkit.jetvive.helper

import academy.bangkit.jetvive.data.repository.MoodRepository
import academy.bangkit.jetvive.data.repository.OnboardingRepository
import academy.bangkit.jetvive.data.repository.TouristAttractionRepository
import academy.bangkit.jetvive.data.repository.UserRepository
import academy.bangkit.jetvive.di.Injection
import academy.bangkit.jetvive.main.MainViewModel
import academy.bangkit.jetvive.ui.screen.detail.DetailViewModel
import academy.bangkit.jetvive.ui.screen.home.HomeViewModel
import academy.bangkit.jetvive.ui.screen.login.LoginViewModel
import academy.bangkit.jetvive.ui.screen.onboarding.OnboardingViewModel
import academy.bangkit.jetvive.ui.screen.profile.ProfileViewModel
import academy.bangkit.jetvive.ui.screen.register.RegisterViewModel
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(
    private val onboardingRepository: OnboardingRepository,
    private val userRepository: UserRepository,
    private val moodRepository: MoodRepository,
    private val touristAttractionRepository: TouristAttractionRepository
    ): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(OnboardingViewModel::class.java) -> {
                OnboardingViewModel(onboardingRepository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(touristAttractionRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(touristAttractionRepository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(userRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(
                Injection.provideOnboardingRepository(),
                Injection.provideUserRepository(context),
                Injection.provideMoodRepository(),
                Injection.provideTouristAttractionRepository()
            )
        }.also { instance = it }
    }
}
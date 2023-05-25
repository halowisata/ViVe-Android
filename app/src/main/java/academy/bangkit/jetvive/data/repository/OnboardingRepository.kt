package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.model.FakeOnboardingDataSource
import academy.bangkit.jetvive.model.Onboarding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class OnboardingRepository {

    fun getAllOnboardingData(): Flow<List<Onboarding>> =
        flowOf(FakeOnboardingDataSource.dummyOnboardings)

    companion object {
        @Volatile
        private var instance: OnboardingRepository? = null

        fun getInstance(): OnboardingRepository =
            instance ?: synchronized(this) {
                OnboardingRepository().apply {
                    instance = this
                }
            }
    }
}
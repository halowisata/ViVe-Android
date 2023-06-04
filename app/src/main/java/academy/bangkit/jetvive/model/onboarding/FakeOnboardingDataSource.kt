package academy.bangkit.jetvive.model.onboarding

import academy.bangkit.jetvive.R

object FakeOnboardingDataSource {
    val dummyOnboardings = listOf(
        Onboarding(
            R.drawable.onboarding_1,
            R.string.first_onboarding,
            R.string.first_onboarding_description
        ),
        Onboarding(
            R.drawable.onboarding_2,
            R.string.second_onboarding,
            R.string.second_onboarding_description
        ),
        Onboarding(
            R.drawable.onboarding_3,
            R.string.third_onboarding,
            R.string.third_onboarding_description
        )
    )
}
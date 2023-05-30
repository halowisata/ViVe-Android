package academy.bangkit.jetvive.model.onboarding

import academy.bangkit.jetvive.R

object FakeOnboardingDataSource {
    val dummyOnboardings = listOf(
        Onboarding(
            R.drawable.onboarding_1,
            "Discover Your Perfect Gateway",
            "Find your dream travel destinations effortlessly with our mood-based " +
                    "recommender system"
        ),
        Onboarding(
            R.drawable.onboarding_2,
            "Set Your Mood, Find Your Adventure",
            "Tell us how you feel and let us curate personalized travel recommendations " +
                    "just for you"
        ),
        Onboarding(
            R.drawable.onboarding_3,
            "Explore, Experience, Enjoy",
            "Embark on unforgottable journeys tailored to your mood and uncover hidden " +
                    "gems around the world"
        )
    )
}
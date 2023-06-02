package academy.bangkit.jetvive.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding: Screen("onboarding")
    object Login: Screen("login")
    object Register: Screen("register")
    object Mood: Screen("mood")
    object Survey: Screen("survey")
    object Home: Screen("home")
    object Bookmark: Screen("bookmark")
    object profile: Screen("profile")
    object DetailTouristAttraction: Screen("home/{touristAttractionId}") {
        fun createRoute(touristAttraction: String) = "home/$touristAttraction"
    }
}
package academy.bangkit.jetvive.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Bookmark: Screen("bookmark")
    object profile: Screen("profile")
    object DetailTouristAttraction: Screen("home/{touristAttractionId}") {
        fun createRoute(touristAttraction: Long) = "home/$touristAttraction"
    }
}
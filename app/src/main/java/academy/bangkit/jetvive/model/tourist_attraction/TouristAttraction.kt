package academy.bangkit.jetvive.model.tourist_attraction

data class TouristAttraction(
    val id: String,
    val name: String,
    val description: String,
    val image: Int,
    val city: String,
    val rating: Double,
    val lat: Double? = null,
    val lon: Double? = null
)
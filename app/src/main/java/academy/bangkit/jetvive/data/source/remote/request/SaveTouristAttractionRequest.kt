package academy.bangkit.jetvive.data.source.remote.request

data class SaveTouristAttractionRequest(
    val name: String,
    val city: String,
    val description: String,
    val rating: Double,
    val lat: Double,
    val lon: Double
)

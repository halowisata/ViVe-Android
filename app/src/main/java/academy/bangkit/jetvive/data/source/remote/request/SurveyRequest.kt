package academy.bangkit.jetvive.data.source.remote.request

data class SurveyRequest(
    val mood: String,
    val budget: String,
    val travelDistance: String,
    val destinationCity: String
)
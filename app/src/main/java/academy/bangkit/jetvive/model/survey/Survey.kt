package academy.bangkit.jetvive.model.survey

data class Survey(
    val id: String,
    val userId: String,
    val moodId: String,
    val budget: String,
    val travelDistance: Int,
    val destinationCity: String
)

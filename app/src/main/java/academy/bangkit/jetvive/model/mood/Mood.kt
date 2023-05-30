package academy.bangkit.jetvive.model.mood

data class Mood(
    val id: String,
    val name: String,
    val image: Int,
    val color: Long,
    val surveyId: String? = null
)
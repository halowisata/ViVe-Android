package academy.bangkit.jetvive.model.user

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val moodId: String? = null
)

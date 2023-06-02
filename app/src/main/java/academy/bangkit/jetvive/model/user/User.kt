package academy.bangkit.jetvive.model.user

data class User(
    val id: String,
    val name: String,
    val username: String,
    val email: String,
    val password: String,
    val phoneNumber: String,
    val address: String,
    val gender: String
)
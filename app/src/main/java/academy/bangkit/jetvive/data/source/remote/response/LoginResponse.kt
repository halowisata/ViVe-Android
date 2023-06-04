package academy.bangkit.jetvive.data.source.remote.response

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val loginResult: LoginResultResponse
)

package academy.bangkit.jetvive.data.source.remote.retrofit

sealed class ApiResponse<out R> private constructor() {
    data class Success<out T>(val data: T): ApiResponse<T>()
    data class Error(val error: String): ApiResponse<Nothing>()
    object Empty: ApiResponse<Nothing>()
}

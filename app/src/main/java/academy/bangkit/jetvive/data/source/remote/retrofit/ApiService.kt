package academy.bangkit.jetvive.data.source.remote.retrofit

import academy.bangkit.jetvive.data.source.remote.request.LoginRequest
import academy.bangkit.jetvive.data.source.remote.request.LogoutRequest
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.data.source.remote.request.SurveyRequest
import academy.bangkit.jetvive.data.source.remote.response.LoginResponse
import academy.bangkit.jetvive.data.source.remote.response.LogoutResponse
import academy.bangkit.jetvive.data.source.remote.response.RegisterResponse
import academy.bangkit.jetvive.data.source.remote.response.SurveyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("users")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("authentications")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @HTTP(method = "DELETE", path = "authentications", hasBody = true)
    suspend fun logout(@Body logoutRequest: LogoutRequest): Response<LogoutResponse>

    @POST("surveys")
    suspend fun addSurvey(
        @Header("Authorization") accessToken: String,
        @Body surveyRequest: SurveyRequest
    ): Response<SurveyResponse>
}
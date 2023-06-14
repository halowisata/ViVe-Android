package academy.bangkit.jetvive.data.source.remote.retrofit

import academy.bangkit.jetvive.data.source.remote.request.LoginRequest
import academy.bangkit.jetvive.data.source.remote.request.LogoutRequest
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.data.source.remote.request.SurveyRequest
import academy.bangkit.jetvive.data.source.remote.response.GetSurveyResponse
import academy.bangkit.jetvive.data.source.remote.response.LoginResponse
import academy.bangkit.jetvive.data.source.remote.response.LogoutResponse
import academy.bangkit.jetvive.data.source.remote.response.RegisterResponse
import academy.bangkit.jetvive.data.source.remote.response.PostSurveyResponse
import academy.bangkit.jetvive.data.source.remote.response.TouristAttractionsResponse
import academy.bangkit.jetvive.data.source.remote.response.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

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
    ): Response<PostSurveyResponse>

    @GET("surveys")
    suspend fun getSurvey(@Header("Authorization") accessToken: String): Response<GetSurveyResponse>

    @GET("users")
    suspend fun getUser(@Header("Authorization") accessToken: String): Response<UserResponse>

    @GET("tourist-attractions")
    suspend fun getTouristAttractions(
        @Header("Authorization") accessToken: String,
        @Query("mood") mood: String,
        @Query("budget") budget: String,
        @Query("city") city: String
    ): Response<TouristAttractionsResponse>
}
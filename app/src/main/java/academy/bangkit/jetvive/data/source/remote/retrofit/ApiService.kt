package academy.bangkit.jetvive.data.source.remote.retrofit

import academy.bangkit.jetvive.data.source.remote.request.LoginRequest
import academy.bangkit.jetvive.data.source.remote.request.RegisterRequest
import academy.bangkit.jetvive.data.source.remote.response.LoginResponse
import academy.bangkit.jetvive.data.source.remote.response.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("users")
    suspend fun register(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("authentications")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
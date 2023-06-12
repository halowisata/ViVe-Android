package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.source.remote.request.SurveyRequest
import academy.bangkit.jetvive.data.source.remote.response.SurveyResponse
import academy.bangkit.jetvive.data.source.remote.retrofit.ApiService
import academy.bangkit.jetvive.ui.common.UiState

class SurveyRepository(private val apiService: ApiService) {

    suspend fun addSurvey(accessToken: String, surveyRequest: SurveyRequest): UiState<SurveyResponse> {
        return try {
            val response = apiService.addSurvey(
                "Bearer $accessToken",
                surveyRequest
            )
            if (response.isSuccessful) {
                UiState.Success(response.body() ?: throw Exception("Empty response body"))
            } else {
                UiState.Error("Add survey failed")
            }
        } catch (exception: Exception) {
            UiState.Error(exception.message.toString())
        }
    }

    companion object {
        @Volatile
        private var instance: SurveyRepository? = null

        fun getInstance(apiService: ApiService): SurveyRepository = instance ?: synchronized(this) {
            SurveyRepository(apiService).apply {
                instance = this
            }
        }
    }
}
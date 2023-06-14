package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.source.remote.response.TouristAttractionsResponse
import academy.bangkit.jetvive.data.source.remote.retrofit.ApiService
import academy.bangkit.jetvive.model.tourist_attraction.FakeTouristAttractionDataSource
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import academy.bangkit.jetvive.ui.common.UiState

class TouristAttractionRepository(private val apiService: ApiService) {

    suspend fun getAllTouristAttractions(
        accessToken: String,
        mood: String,
        budget: String,
        city: String
    ): UiState<TouristAttractionsResponse> {
        return try {
            val response = apiService.getTouristAttractions(
                "Bearer $accessToken",
                mood,
                budget,
                city
            )
            if (response.isSuccessful) {
                UiState.Success(response.body() ?: throw Exception("Empty response body"))
            } else {
                UiState.Error("Get tourist attractions failed")
            }
        } catch (exception: Exception) {
            UiState.Error(exception.message.toString())
        }
    }

    fun getDetailTouristAttractionById(touristAttractionId: String): TouristAttraction =
        FakeTouristAttractionDataSource.dummyTouristAttractions.first {
            it.id == touristAttractionId
        }

    companion object {
        @Volatile
        private var instance: TouristAttractionRepository? = null

        fun getInstance(
            apiService: ApiService
        ): TouristAttractionRepository = instance ?: synchronized(this) {
            TouristAttractionRepository(apiService).apply {
                instance = this
            }
        }
    }
}
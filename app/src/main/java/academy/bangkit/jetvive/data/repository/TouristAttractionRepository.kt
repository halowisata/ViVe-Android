package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.source.remote.request.SaveTouristAttractionRequest
import academy.bangkit.jetvive.data.source.remote.response.DeleteSavedTouristAttractionResponse
import academy.bangkit.jetvive.data.source.remote.response.GetSavedTouristAttractionsResponse
import academy.bangkit.jetvive.data.source.remote.response.PostSavedTouristAttractionResponse
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

    suspend fun saveTouristAttraction(
        accessToken: String,
        saveTouristAttractionRequest: SaveTouristAttractionRequest
    ): UiState<PostSavedTouristAttractionResponse> {
        return try {
            val response = apiService.saveTouristAttraction(
                "Bearer $accessToken",
                saveTouristAttractionRequest
            )
            if (response.isSuccessful) {
                UiState.Success(response.body() ?: throw Exception("Empty response body"))
            } else {
                UiState.Error("Save tourist attraction failed")
            }
        } catch (exception: Exception) {
            UiState.Error(exception.message.toString())
        }
    }

    suspend fun getSavedTouristAttractions(
        accessToken: String
    ): UiState<GetSavedTouristAttractionsResponse> {
        return  try {
            val response = apiService.getSavedTouristAttractions(
                "Bearer $accessToken"
            )
            if (response.isSuccessful) {
                UiState.Success(response.body() ?: throw Exception("Empty response body"))
            } else {
                UiState.Error("Get saved tourist attractions failed")
            }
        } catch (exception: Exception) {
            UiState.Error(exception.message.toString())
        }
    }

    suspend fun deleteSavedTouristAttraction(
        accessToken: String,
        touristAttractionName: String
    ): UiState<DeleteSavedTouristAttractionResponse> {
        return try {
            val response =
                apiService.deleteSavedTouristAttraction(
                    "Bearer $accessToken",
                    touristAttractionName
                )
            if (response.isSuccessful) {
                UiState.Success(response.body() ?: throw Exception("Empty response body"))
            } else {
                UiState.Error("Delete saved tourist attraction failed")
            }
        } catch (exception: Exception) {
            UiState.Error(exception.message.toString())
        }
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
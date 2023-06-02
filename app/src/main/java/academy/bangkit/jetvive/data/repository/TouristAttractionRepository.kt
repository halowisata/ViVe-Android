package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.model.tourist_attraction.FakeTouristAttractionDataSource
import academy.bangkit.jetvive.model.tourist_attraction.TouristAttraction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TouristAttractionRepository {

    fun getAllTouristAttractions(): Flow<List<TouristAttraction>> =
        flowOf(FakeTouristAttractionDataSource.dummyTouristAttractions)

    fun getDetailTouristAttractionById(id: String): TouristAttraction =
        FakeTouristAttractionDataSource.dummyTouristAttractions.first {
            it.id == id
        }

    companion object {
        @Volatile
        private var instance: TouristAttractionRepository? = null

        fun getInstance(): TouristAttractionRepository = instance ?: synchronized(this) {
            TouristAttractionRepository().apply {
                instance = this
            }
        }
    }
}
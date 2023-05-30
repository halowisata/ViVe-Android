package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.model.mood.FakeMoodDataSource
import academy.bangkit.jetvive.model.mood.Mood
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MoodRepository {

    fun getAllMoods(): Flow<List<Mood>> = flowOf(FakeMoodDataSource.dummyMoods)

    companion object {
        @Volatile
        private var instance: MoodRepository? = null

        fun getInstance(): MoodRepository =
            instance ?: synchronized(this) {
                MoodRepository().apply {
                    instance = this
                }
            }
    }
}
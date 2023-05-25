package academy.bangkit.jetvive.data.repository

class TouristAttractionRepository {

    companion object {
        @Volatile
        private var instance: TouristAttractionRepository? = null

        fun getInstance(): TouristAttractionRepository =
            instance ?: synchronized(this) {
                TouristAttractionRepository().apply {
                    instance = this
                }
            }
    }
}
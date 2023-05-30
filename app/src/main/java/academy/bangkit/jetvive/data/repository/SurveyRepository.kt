package academy.bangkit.jetvive.data.repository

class SurveyRepository {

    companion object {
        @Volatile
        private var instance: SurveyRepository? = null

        fun getInstance(): SurveyRepository =
            instance ?: synchronized(this) {
                SurveyRepository().apply {
                    instance = this
                }
            }
    }
}
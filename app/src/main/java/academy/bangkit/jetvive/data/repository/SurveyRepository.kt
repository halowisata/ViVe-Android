package academy.bangkit.jetvive.data.repository

import academy.bangkit.jetvive.data.source.remote.request.SurveyRequest
import academy.bangkit.jetvive.model.survey.Survey

class SurveyRepository {

    private val surveys = mutableListOf<Survey>()

    fun addSurvey(surveyRequest: SurveyRequest) {
        surveys.add(
            Survey(
                id = "survey",
                userId = surveyRequest.userId,
                moodId = surveyRequest.moodId,
                budget = surveyRequest.budget,
                travelDistance = surveyRequest.travelDistance,
                destinationCity = surveyRequest.destinationCity
            )
        )
    }

    companion object {
        @Volatile
        private var instance: SurveyRepository? = null

        fun getInstance(): SurveyRepository = instance ?: synchronized(this) {
            SurveyRepository().apply {
                instance = this
            }
        }
    }
}
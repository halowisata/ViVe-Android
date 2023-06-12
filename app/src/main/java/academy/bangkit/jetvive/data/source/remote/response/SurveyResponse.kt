package academy.bangkit.jetvive.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SurveyResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: SurveyData
)

data class SurveyData(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("userId")
    val userId: String,

    @field:SerializedName("moodId")
    val moodId: String,

    @field:SerializedName("budget")
    val budget: String,

    @field:SerializedName("travelDistance")
    val travelDistance: String,

    @field:SerializedName("destinationCity")
    val destinationCity: String,

    @field:SerializedName("createdAt")
    val createdAt: String
)
package academy.bangkit.jetvive.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PostSurveyResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: PostSurveyData
)

data class PostSurveyData(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("mood")
    val mood: String,

    @field:SerializedName("budget")
    val budget: String,

    @field:SerializedName("travelDistance")
    val travelDistance: String,

    @field:SerializedName("destinationCity")
    val destinationCity: String,

    @field:SerializedName("createdAt")
    val createdAt: String
)
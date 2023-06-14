package academy.bangkit.jetvive.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class PostSavedTouristAttractionResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: PostSavedTouristAttractionData
)

data class PostSavedTouristAttractionData(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("city")
    val city: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("rating")
    val rating: Double,

    @field:SerializedName("lat")
    val lat: Double,

    @field:SerializedName("lon")
    val lon: Double,

    @field:SerializedName("createdAt")
    val createdAt: String
)
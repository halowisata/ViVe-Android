package academy.bangkit.jetvive.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DeleteSavedTouristAttractionResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

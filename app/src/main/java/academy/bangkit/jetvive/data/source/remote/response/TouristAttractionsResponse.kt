package academy.bangkit.jetvive.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TouristAttractionsResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<TouristAttractionsData>
)

data class TouristAttractionsData(
    @field:SerializedName("Place_Id")
    val id: Int,

    @field:SerializedName("Place_Name")
    val name: String,

    @field:SerializedName("City")
    val city: String,

    @field:SerializedName("Description")
    val description: String,
)
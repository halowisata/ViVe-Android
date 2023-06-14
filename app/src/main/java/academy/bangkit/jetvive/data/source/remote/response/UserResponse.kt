package academy.bangkit.jetvive.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: UserData
)

data class UserData(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("phoneNumber")
    val phoneNumber: String,

    @field:SerializedName("address")
    val address: String,

    @field:SerializedName("age")
    val age: Int,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("photo")
    val photo: String
)
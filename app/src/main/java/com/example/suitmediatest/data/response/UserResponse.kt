package com.example.suitmediatest.data.response

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("data") val data: List<DataItem?>? = null
)

@Entity(tableName = "users")
data class DataItem(
    @field:SerializedName("id") val id: Int? = null,

    @field:SerializedName("email") val email: String? = null,

    @field:SerializedName("first_name") val firstName: String? = null,

    @field:SerializedName("last_name") val lastName: String? = null,

    @field:SerializedName("avatar") val avatar: String? = null
)


package com.d121211020.cnnterbaru.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Data(
    @SerialName("description")
    val description: String,
    @SerialName("image")
    val image: String,
    @SerialName("link")
    val link: String,
    @SerialName("posts")
    val posts: List<Post>,
    @SerialName("title")
    val title: String
): Parcelable
package com.d121211020.cnnterbaru.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Post(
    @SerialName("description")
    val description: String,
    @SerialName("link")
    val link: String,
    @SerialName("pubDate")
    val pubDate: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String
): Parcelable

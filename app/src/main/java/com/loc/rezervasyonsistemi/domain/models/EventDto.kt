package com.loc.rezervasyonsistemi.domain.models

import com.google.gson.annotations.SerializedName

data class EventDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_url")
    val posterUrl: String,

    @SerializedName("duration_minutes")
    val durationMinutes: Int,

    @SerializedName("genre")
    val genre: String
)

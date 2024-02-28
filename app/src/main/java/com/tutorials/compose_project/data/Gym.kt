package com.tutorials.compose_project.data

import com.google.gson.annotations.SerializedName




data class Gym(
    var id: Int,
    @SerializedName("gym_name")
    val name: String,
    @SerializedName("gym_location")
    val place: String,
    var isFavorite: Boolean = false)

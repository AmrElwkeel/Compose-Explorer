package com.tutorials.compose_project.domain

import com.tutorials.compose_project.data.Gym
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GymsApiService {

    @GET("gyms.json")
    suspend fun getGyms(): List<Gym>

    @GET("gyms.json?orderBy=\"id\"")
    suspend fun getGymDetail(@Query("equalTo") id: Int): Map<String, Gym>
}

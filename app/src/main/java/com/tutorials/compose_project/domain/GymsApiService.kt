package com.tutorials.compose_project.domain
import com.tutorials.compose_project.data.Gym
import retrofit2.Call
import retrofit2.http.GET

interface GymsApiService {

    @GET("gyms.json")
    fun getGyms():Call<List<Gym>>
}

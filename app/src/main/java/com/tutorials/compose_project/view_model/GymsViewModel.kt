package com.tutorials.compose_project.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tutorials.compose_project.data.Gym
import com.tutorials.compose_project.domain.GymsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {


    private var apiService:GymsApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl("https://cairogyms-e1e9a-default-rtdb.firebaseio.com/").build()

         apiService = retrofit.create(GymsApiService::class.java)
        getGyms()
    }

    var state by mutableStateOf(emptyList<Gym>())

    private fun getGyms() {
        apiService.getGyms().enqueue(object: Callback<List<Gym>> {
            override fun onResponse(call: Call<List<Gym>>, response: Response<List<Gym>>) {
                response.body()?.let {
                    state = it.restoreSelectedGyms()
                }
            }

            override fun onFailure(call: Call<List<Gym>>, t: Throwable) {
                      t.printStackTrace()
            }
        })

    }

    fun toggleFavoriteState(gymId: Int) {
        val gyms = state.toMutableList()
        val itemIndex = gyms.indexOfFirst { it.id == gymId }
        gyms[itemIndex] = gyms[itemIndex].copy(isFavorite = !gyms[itemIndex].isFavorite)
        storeSelectedGym(gyms[itemIndex])
        state = gyms
    }

    private fun storeSelectedGym(gym: Gym) {
        val savedHandleList = stateHandle.get<List<Int>?>(FAV_IDS).orEmpty().toMutableList()
        if (gym.isFavorite) savedHandleList.add(gym.id)
        else savedHandleList.remove(gym.id)
        stateHandle[FAV_IDS] = savedHandleList


    }

    private  fun List<Gym>.restoreSelectedGyms():List<Gym>{

//        val gyms =this
        stateHandle.get<List<Int>?>(FAV_IDS)?.let {savedIDs ->
           savedIDs.forEach{gymId ->
           this.find { it.id == gymId }?.isFavorite == true
           }
        }
        return this
    }

    companion object {
        const val FAV_IDS = "favoriteGymsIDs"
    }

}

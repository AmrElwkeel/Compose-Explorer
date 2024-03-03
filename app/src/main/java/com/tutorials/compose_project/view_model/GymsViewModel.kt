package com.tutorials.compose_project.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorials.compose_project.data.Gym
import com.tutorials.compose_project.domain.GymsApiService
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {


    private var apiService: GymsApiService


    private  val errorHandler = CoroutineExceptionHandler{ _, throwable ->


        throwable.printStackTrace() }
    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl("https://cairogyms-e1e9a-default-rtdb.firebaseio.com/").build()

        apiService = retrofit.create(GymsApiService::class.java)
        getGyms()
    }

    var state by mutableStateOf(emptyList<Gym>())

    private fun getGyms() {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
                val gyms = apiService.getGyms()
                withContext(Dispatchers.Main) {
                    state = gyms.restoreSelectedGyms()
                }
        }


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


    private fun List<Gym>.restoreSelectedGyms(): List<Gym> {

//        val gyms =this
        stateHandle.get<List<Int>?>(FAV_IDS)?.let { savedIDs ->
            savedIDs.forEach { gymId ->
                this.find { it.id == gymId }?.isFavorite == true
            }
        }
        return this
    }

    companion object {
        const val FAV_IDS = "favoriteGymsIDs"
    }

}

package com.tutorials.compose_project.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tutorials.compose_project.data.Gym
import com.tutorials.compose_project.data.ListOfGyms

class GymsViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    var state by mutableStateOf(restoreSelectedGyms())

    private fun getGyms() = ListOfGyms

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

    private  fun restoreSelectedGyms():List<Gym>{

        val gyms = getGyms()
        stateHandle.get<List<Int>?>(FAV_IDS)?.let {savedIDs ->
           savedIDs.forEach{gymId ->
           gyms.find { it.id == gymId }?.isFavorite == true
           }
        }
        return gyms
    }

    companion object {
        const val FAV_IDS = "favoriteGymsIDs"
    }

}

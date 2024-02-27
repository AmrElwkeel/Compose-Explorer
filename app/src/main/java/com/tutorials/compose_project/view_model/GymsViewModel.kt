package com.tutorials.compose_project.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tutorials.compose_project.data.ListOfGyms

class GymsViewModel():ViewModel(){

    var state by mutableStateOf(getGyms())

    private  fun getGyms() = ListOfGyms

    fun toggleFavoriteState(gymId : Int){
        val gyms = state.toMutableList()
        val itemIndex =gyms.indexOfFirst { it.id == gymId }
        gyms[itemIndex]=gyms[itemIndex].copy(isFavorite = !gyms[itemIndex].isFavorite)
        state = gyms
    }
}

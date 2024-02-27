package com.tutorials.compose_project.view_model

import androidx.lifecycle.ViewModel
import com.tutorials.compose_project.data.ListOfGyms

class GymsViewModel():ViewModel(){


    fun getGyms() = ListOfGyms
}

package com.tutorials.compose_project.view_model



import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutorials.compose_project.data.Gym
import com.tutorials.compose_project.domain.GymsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class GymDetailsViewModel(
    private  val saveStateHandel :SavedStateHandle
) : ViewModel(){

    val state  = mutableStateOf<Gym?>(null)
    private var apiService: GymsApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create()
        ).baseUrl("https://cairogyms-e1e9a-default-rtdb.firebaseio.com/").build()

        apiService = retrofit.create(GymsApiService::class.java)
        val gymId = saveStateHandel.get<Int>("gym_id")?:0
        getGym(gymId)

    }
    private  fun getGym(id: Int){
        viewModelScope.launch {
            val gym = getGymFromRemoteDB(id)
            state.value = gym
        }
    }
    private suspend  fun getGymFromRemoteDB(id: Int) = withContext(Dispatchers.IO){
          apiService.getGymDetail(id).values.first()
    }

}

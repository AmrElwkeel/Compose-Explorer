package com.tutorials.compose_project.presentation.ui

import GymDetails
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tutorials.compose_project.view_model.GymDetailsViewModel

@Composable


fun GymDetailsScreen() {
    val viewModel: GymDetailsViewModel = viewModel()

    val item = viewModel.state.value
    item?.let {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            DefaultIcon(
                icon = Icons.Filled.Place, modifier = Modifier.padding(bottom = 23.dp),
                contentDescription = "Location Icon"
            )
            GymDetails(
                gym = item,
                modifier = Modifier.padding(bottom = 23.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            Text(
                text = if (item.isOpen) "" else "",
                color = if (item.isOpen) Color.Green else Color.Red
            )
        }

    }


}

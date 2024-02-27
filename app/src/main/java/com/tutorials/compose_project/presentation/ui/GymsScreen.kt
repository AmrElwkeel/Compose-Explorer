package com.tutorials.compose_project.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tutorials.compose_explorer.presentation.theme.Compose_ProjectTheme
import com.tutorials.compose_project.data.Gym
import com.tutorials.compose_project.data.ListOfGyms
import com.tutorials.compose_project.view_model.GymsViewModel


@Composable
fun GymsScreen() {
    val vm:GymsViewModel = viewModel()
    LazyColumn() {
      items(vm.getGyms()){ gym ->
            GymItem(gym)
        }
    }

}

@Composable
fun GymItem(gym:Gym) {
    Card(modifier = Modifier
        .padding(8.dp)
        .height(51.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GymIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            GymDetails(gym,Modifier.weight(0.85f))
            FavoriteIcon(Modifier.weight(0.15f))

        }
    }
}

@Composable
fun FavoriteIcon(modifier: Modifier) {
    var isFavouriteState by remember{ mutableStateOf(false) }
    val isChangeIcon = if(isFavouriteState){
        Icons.Filled.Favorite
    }else{
        Icons.Filled.FavoriteBorder
    }

    Image(
        imageVector = isChangeIcon,
        contentDescription ="Favorite Gym Icon",
        modifier = Modifier.padding(8.dp).clickable {
            isFavouriteState = !isFavouriteState
        }

    )


}

@Composable
fun GymIcon(vector: ImageVector, modifier: Modifier) {

    Image(
        imageVector = vector,
        contentDescription = "Gym Icon",
        modifier = modifier,
        colorFilter = ColorFilter.tint(
            Color.Gray
        )


    )
}


@Composable
fun GymDetails(gym: Gym, modifier: Modifier) {
    Column(modifier=modifier) {
        Text(text = gym.name, style = TextStyle(fontSize = 20.sp, color = Color.Blue))
        CompositionLocalProvider(value = LocalContentColor provides Color.DarkGray) {
            Text(
                text = gym.place,
                style = TextStyle(fontSize = 10.sp)
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun GymScreenPreview() {

//    Compose_ProjectTheme{
        GymsScreen()
//    }

}

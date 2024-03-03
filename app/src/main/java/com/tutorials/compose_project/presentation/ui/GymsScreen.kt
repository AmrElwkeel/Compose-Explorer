import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.tutorials.compose_project.data.Gym
import com.tutorials.compose_project.presentation.ui.DefaultIcon

import com.tutorials.compose_project.view_model.GymsViewModel


@Composable
fun GymsScreen( onItemClick: (Int) -> Unit) {
    val vm: GymsViewModel = viewModel()


    LazyColumn() {
        items(vm.state) { gym ->
            GymItem(
                gym =gym,
                onFavoriteIconClick = { vm.toggleFavoriteState(it) },
                onItemClick = { id -> onItemClick(id)}
            )
        }
    }
}

@Composable
fun GymItem(gym: Gym, onFavoriteIconClick: (Int) -> Unit, onItemClick: (Int) -> Unit) {
    val icon = if (gym.isFavorite) {
        Icons.Filled.Favorite
    } else {
        Icons.Filled.FavoriteBorder
    }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .height(80.dp)
            .clickable { onItemClick(gym.id) }
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GymIcon(Icons.Filled.Place, Modifier.weight(0.15f))
            GymDetails(gym, Modifier.weight(0.85f))
            DefaultIcon(icon, "Favorite Gym Icon", Modifier.weight(0.15f)) {
                onFavoriteIconClick(gym.id)
            }
        }
    }
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
fun GymDetails(
    gym: Gym,
    modifier: Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlignment) {
        Text(text = gym.name, style = TextStyle(fontSize = 20.sp, color = Color.Blue))
        CompositionLocalProvider(value = LocalContentColor provides Color.DarkGray) {
            Text(
                text = gym.place,
                style = TextStyle(fontSize = 10.sp)
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun GymScreenPreview() {
//    GymsScreen()
//}

package com.tutorials.compose_explorer

import GymsScreen
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutorials.compose_explorer.presentation.theme.Compose_ProjectTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var score by remember { mutableStateOf(0) }
            /*       ScoreSection(score = score,
                countCallback = { score += 2 }
            )*/

            Compose_ProjectTheme{
                GymsScreen()
            }
//            MyLayout()

        }
    }
}


@Preview
@Composable
fun MyBox() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(Color.Black)
            .padding(10.dp)
            .clip(RoundedCornerShape(size = 10.dp))
            .background(Color.Gray)
    ) {
        Text(text = "Hello", Modifier.align(Alignment.TopCenter), color = Color.White)
        Text(text = "Android", Modifier.align(Alignment.Center), color = Color.White)
        Text(text = "Developers", Modifier.align(Alignment.BottomCenter), color = Color.White)
    }

}

@Preview
@Composable
fun MyLayout() {

    Column {
        MyText()
        MyTexButton()
        Row {
            Text(text = "Logo")
            MyImage()

        }
    }
}


@Composable
fun MyText() {
    Text(
        text = "Elephant Can sens Storms",
        style = androidx.compose.ui.text.TextStyle(
            color = Color.Green,
            fontSize = 20.sp
        )
    )
}


@Preview(showBackground = true)
@Composable
fun MyTexButton() {
    var buttonIsEnable by remember { mutableStateOf(true) }
    Button(
        onClick = { buttonIsEnable = false },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.Red,
            disabledContainerColor = Color.Gray

        ),
        enabled = buttonIsEnable
    ) {
        Text(
            text = if (buttonIsEnable) "Click Me" else "Elephant Button",
            style = androidx.compose.ui.text.TextStyle(
                color = Color.Green,
                fontSize = 20.sp
            )
        )

    }
}


@Composable
fun MyTextField() {
    var emailAddress by remember {
        mutableStateOf("")
    }
    TextField(value = emailAddress, onValueChange = {
        emailAddress = it
    },
        label = { Text(text = "Email Address") })
}


@Composable
fun MyImage() {
    Image(
        painter = painterResource(id = R.drawable.jetpack_compose),
        contentDescription = "Jetpack Compose Logo"
    )
}


/// Clean Architecture principles
//app
//|-- src/
//|   |-- main/
//|       |-- java/
//|       |-- kotlin/
//|           |-- com/
//|               |-- yourpackage/
//|                   |-- di/
//|                       |-- AppContainer.kt
//|                   |-- domain/
//|                       |-- model/
//|                           |-- Entity1.kt
//|                           |-- Entity2.kt
//|                       |-- repository/
//|                           |-- Repository1.kt
//|                           |-- Repository2.kt
//|                       |-- usecase/
//|                           |-- UseCase1.kt
//|                           |-- UseCase2.kt
//|                   |-- data/
//|                       |-- model/
//|                           |-- DataEntity1.kt
//|                           |-- DataEntity2.kt
//|                       |-- repository/
//|                           |-- Repository1Impl.kt
//|                           |-- Repository2Impl.kt
//|                       |-- datasource/
//|                           |-- RemoteDataSource.kt
//|                           |-- LocalDataSource.kt
//|                   |-- presentation/
//|                       |-- theme/
//|                           |-- AppTheme.kt
//|                       |-- ui/
//|                           |-- MainActivity.kt
//|                           |-- feature1/
//|                               |-- Feature1Screen.kt
//|                               |-- Feature1ViewModel.kt
//|                           |-- feature2/
//|                               |-- Feature2Screen.kt
//|                               |-- Feature2ViewModel.kt
//|-- build.gradle
//|-- settings.gradle

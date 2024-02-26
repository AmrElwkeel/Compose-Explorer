package com.tutorials.compose_explorer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScoreSection(score: Int, countCallback: () -> Unit) {

    Column {

        Text(text = "Score : $score")
        Button(onClick = countCallback) {
            Text(text = "Add Points")
        }
    }

}

@Preview(
    name = "Preview1",
    showSystemUi = true,
    showBackground = true,
    device = Devices.NEXUS_7,
    )
@Composable
fun PreviewScoreSection1() {
    var score by remember { mutableStateOf(0) }
    ScoreSection(score = score,
        countCallback = { score += 2 }
    )
}


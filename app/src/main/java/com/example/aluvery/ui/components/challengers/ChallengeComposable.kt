package com.example.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChallengerCompose() {
    Row(Modifier.width(150.dp)) {
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .height(50.dp)
                .width(40.dp)
        )
        Column {
            Text(text = "Test 1", modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray))
            Text(text = "Test 2", modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengerComposePreview() {
    ChallengerCompose()
}
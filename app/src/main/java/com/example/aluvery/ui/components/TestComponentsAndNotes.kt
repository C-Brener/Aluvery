package com.example.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.ui.theme.AluveryTheme

@Composable
fun MyFirstComposable() {
    Text(text = "Meu Primeiro Texto")
    Text(text = "Meu segundo texto maior")
}

//@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Preview(showSystemUi = true, name = "TextPreview")
@Composable
fun MyFirstComposablePreview() {
    AluveryTheme {
        Surface() {
            MyFirstComposable()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnPreview() {
    Column() {
        Text(text = "Composable de coluna")
        Text(text = "Original Android")
    }
}

@Preview(showBackground = true)
@Composable
fun RowPreview() {
    Row {
        Text(text = "Composable de row")
        Text(text = "Original Android")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxPreview() {
    Box {
        Text(text = "Composable de box")
        Text(text = " Original android")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyComposableLayoutPreview() {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(Color.Cyan)
            .fillMaxSize()

    ) {
        Text(text = "Testando diversos layouts")
        Text(text = "Com composables padrões")
    }

}
package com.example.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
    Column {
        var url by remember {
            mutableStateOf("")
        }
        var price by remember {
            mutableStateOf("")
        }
        var name by remember {
            mutableStateOf("")
        }
        var description by remember {
            mutableStateOf("")
        }
        Text(text = "Criando Produto")
        TextField(value = url, onValueChange = {
            url = it
        }, placeholder = { Text(text = "URL") })
        TextField(value = name, onValueChange = {
            name = it
        }, placeholder = { Text(text = "NAME") })
        TextField(value = price, onValueChange = {
            price = it
        }, placeholder = { Text(text = "PRICE") })
        TextField(value = description, onValueChange = {
            description = it
        }, placeholder = { Text(text = "DESCRIPTION") })
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Criar produto")
        }
    }
}

@Preview
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
        }
    }
}



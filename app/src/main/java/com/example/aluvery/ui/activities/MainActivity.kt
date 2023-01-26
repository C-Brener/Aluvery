package com.example.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.screens.HomeScreen
import com.example.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFABClick = {
                startActivity(Intent(
                    this,
                    ProductFormActivity::class.java
                ))
            })
        }
    }
}

@Composable
fun App(onFABClick: () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFABClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)

                }
            }) {
                Box(modifier = Modifier.padding(paddingValues = it)) {
                    HomeScreen(
                        sampleSections
                    )
                }
            }
        }
    }
}
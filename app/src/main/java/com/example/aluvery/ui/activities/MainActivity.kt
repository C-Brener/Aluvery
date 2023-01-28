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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.ui.screens.homescreen.HomeScreen
import com.example.aluvery.ui.screens.homescreen.HomeScreenUIState
import com.example.aluvery.ui.screens.homescreen.ItemsData
import com.example.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    val productDao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFABClick = {
                startActivity(
                    Intent(
                        this,
                        ProductFormActivity::class.java
                    )
                )
            }) {
                val productList = productDao.getList()
                val sections = mapOf(
                    "Todos os produtos" to productList,
                    "Promoções" to productList.filter { it.typeProduct == "Doces" || it.typeProduct == "Bebidas" } + sampleCandies + sampleDrinks,
                    "Doces" to productList.filter { it.typeProduct == "Doces" } + sampleCandies,
                    "Bebidas" to productList.filter { it.typeProduct == "Bebidas" } + sampleDrinks
                )
                val itemData = sections.map {
                    ItemsData(title = it.key, listItems = it.value)
                }
                val state = remember(sections) { HomeScreenUIState(itemsData = itemData) }
                HomeScreen(
                    state = state
                )
            }
        }
    }
}

@Composable
fun App(onFABClick: () -> Unit = {}, content: @Composable () -> Unit = {}) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFABClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)

                }
            }) {
                Box(modifier = Modifier.padding(paddingValues = it)) {
                    content()
                }
            }
        }
    }
}
package com.example.aluvery.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.theme.AluveryTheme

const val TAG: String = "Testando"

@Composable
fun HomeScreen(
    sections: Map<String, List<ProductItemModel>>
) {
    var textInput by remember { mutableStateOf("") }
    val items = sections.map { ItemsData(title = it.key, listItems = it.value) }
    Column {
        OutlinedTextField(value = textInput,
            onValueChange = { value ->
                Log.i(TAG, "HomeScreen: ")
                textInput = value
            },
            label = { Text(text = "Produto") },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(percent = 100),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
            placeholder = {
                Text(text = "O que você procura?")
            })
        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(items) { itemData ->
                if (textInput.isBlank()) {
                    ProductsSection(title = itemData.title, products = itemData.listItems)

                } else {
                    val newList = remember(textInput) {
                        itemData.listItems.filter { productItem ->
                            productItem.name.contains(
                                textInput,
                                ignoreCase = true
                            ) || productItem.description?.contains(textInput) ?: false
                        }
                    }
                    newList.forEach { itemCard ->
                        CardProductItem(
                            product = itemCard, modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}

data class ItemsData(
    val title: String, val listItems: List<ProductItemModel>
)


// ---------- Lazu Column ----------

// * O Lazy Column assim como tod lazy list, não aceita colecctions como parâmetro, apenas listas, para isso precisa-se fazer a conversão de colletion para lista como no modelo acima
// * contentPadding coloca um padding na renderização de cada item.


// ---------- TextField ----------
package com.example.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<ProductItemModel>>
) {
    val itens = sections.map { ItemsData(title = it.key, listItems = it.value) }
    LazyColumn(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(itens) {
            ProductsSection(title = it.title, products = it.listItems)
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
    val title: String,
    val listItems: List<ProductItemModel>
)


// ----------- Lazu Column

// * O Lazy Column assim como tod lazy list, não aceita colecctions como parâmetro, apenas listas, para isso precisa-se fazer a conversão de colletion para lista como no modelo acima
// * contentPadding coloca um padding na renderização de cada item

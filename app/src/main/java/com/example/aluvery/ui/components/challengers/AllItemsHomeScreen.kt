package com.example.aluvery.ui.components.challengers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleSections
import com.example.aluvery.ui.components.ProductItem

@Composable
fun AllItemsHomeScreen(productItemModel: List<ProductItemModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            Text(text = "Todos Produtos", fontSize = 18.sp)
        }
        items(productItemModel) {
            ProductItem(product = it)
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun Teste() {
    val list = sampleSections.map {
        it.value
    }
    AllItemsHomeScreen(productItemModel = list[0])
}

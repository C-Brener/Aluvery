package com.example.aluvery.ui.screens.homescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.components.SearchTextField
import com.example.aluvery.ui.theme.AluveryTheme


@Composable
fun HomeScreenStateFul(productList: List<ProductItemModel>) {
    val sections = mapOf(
        "Todos os produtos" to productList,
        "Promoções" to productList.filter { it.typeProduct == "Doces" || it.typeProduct == "Bebidas" } + sampleCandies + sampleDrinks,
        "Doces" to productList.filter { it.typeProduct == "Doces" } + sampleCandies,
        "Bebidas" to productList.filter { it.typeProduct == "Bebidas" } + sampleDrinks
    )
    val itemData = sections.map {
        ItemsData(title = it.key, listItems = it.value)
    }
    var textInput by remember {
        mutableStateOf("")
    }
    val state = remember(productList, textInput) {
        HomeScreenUIState(itemsData = itemData, textInput, onSearchChange = {
            textInput = it
        })
    }
    HomeScreenStateLess(state = state)
}

@Composable
fun HomeScreenStateLess(
    state: HomeScreenUIState = HomeScreenUIState()
) {
    val items = state.itemDataList()
    val searchedProducts = state.searchedProducts()
    Column {
        SearchTextField(searchText = state.textInput, searchTextChanged = state.onSearchChange)
        LazyColumn(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(items) { itemData ->
                if (state.isShowSections()) {
                    ProductsSection(title = itemData.title, products = itemData.listItems)
                } else {
                    searchedProducts.forEach { itemCard ->
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
            HomeScreenStateLess(HomeScreenUIState(listOf()))
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
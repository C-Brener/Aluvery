package com.example.aluvery.ui.screens.homescreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.components.CardProductItem
import com.example.aluvery.ui.components.ProductsSection
import com.example.aluvery.ui.components.SearchTextField
import com.example.aluvery.ui.states.HomeScreenUIState
import com.example.aluvery.ui.theme.AluveryTheme
import com.example.aluvery.ui.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreenStateFul(viewModel: HomeScreenViewModel) {
    HomeScreenStateLess(state = viewModel.uiState)
}

@Composable
fun HomeScreenStateLess(
    state: HomeScreenUIState
) {
    val items = state.itemsData
    val searchedProducts = state.searchProducts
    Column {
        SearchTextField(
            searchText = state.textInput,
            searchTextChanged = state.onSearchChange
        )
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
                            product = itemCard,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
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
            HomeScreenStateLess(HomeScreenViewModel().uiState)
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
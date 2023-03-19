package com.example.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.ui.screens.homescreen.ItemsData
import com.example.aluvery.ui.states.HomeScreenUIState
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val productDao = ProductDao()
    private fun getList(): List<ItemsData> {
        val createSections = mapOf(
            "Todos os produtos" to productDao.getList().value,
            "Promoções" to productDao.getList()
                .value.filter { it.typeProduct == "Doces" || it.typeProduct == "Bebidas" } + sampleCandies + sampleDrinks,
            "Doces" to productDao.getList().value.filter { it.typeProduct == "Doces" } + sampleCandies,
            "Bebidas" to productDao.getList().value.filter { it.typeProduct == "Bebidas" } + sampleDrinks
        )

        return createSections.map {
            ItemsData(title = it.key, listItems = it.value)
        }
    }

    var uiState: HomeScreenUIState by mutableStateOf(HomeScreenUIState(
        itemsData = getList(),
        onSearchChange = {
            uiState = uiState.copy(textInput = it, searchProducts = searchedProducts(it))
        }
    ))
        private set


    init {
        viewModelScope.launch {
            productDao.getList().collect { products ->
                uiState = uiState.copy(
                    itemsData = getList()
                )
            }
        }
    }

    private fun searchedProducts(text: String): List<ProductItemModel> = if (text.isNotBlank()) {
        sampleProducts.filter(containsInNameOrDescription(text)) + productDao.getList().value.filter(
            containsInNameOrDescription(text)
        )
    } else {
        emptyList<ProductItemModel>()
    }

    fun findProducts() {
        uiState = uiState.copy(
            itemsData = getList()
        )
    }

    private fun containsInNameOrDescription(text: String) = { product: ProductItemModel ->
        product.name.contains(
            text,
            ignoreCase = true,
        ) || product.description?.contains(
            text,
            ignoreCase = true,
        ) ?: false
    }


}

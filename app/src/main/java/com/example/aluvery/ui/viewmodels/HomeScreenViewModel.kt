package com.example.aluvery.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.ui.screens.homescreen.ItemsData
import com.example.aluvery.ui.states.HomeScreenUIState

class HomeScreenViewModel : ViewModel() {
    private val productDao = ProductDao()

    private val sections = mapOf(
        "Todos os produtos" to productDao.getList(),
        "Promoções" to productDao.getList()
            .filter { it.typeProduct == "Doces" || it.typeProduct == "Bebidas" } + sampleCandies + sampleDrinks,
        "Doces" to productDao.getList().filter { it.typeProduct == "Doces" } + sampleCandies,
        "Bebidas" to productDao.getList().filter { it.typeProduct == "Bebidas" } + sampleDrinks
    )

    private val itemsData = sections.map {
        ItemsData(title = it.key, listItems = it.value)
    }
    var uiState: HomeScreenUIState by mutableStateOf(HomeScreenUIState(
        itemsData = itemsData,
        onSearchChange = {
            uiState = uiState.copy(textInput = it, searchProducts = searchedProducts(it))
        }
    ))
        private set

    private fun searchedProducts(text: String): List<ProductItemModel> = if (text.isNotBlank()) {
        sampleProducts.filter(containsInNameOrDescrioption(text)) + productDao.getList().filter(
            containsInNameOrDescrioption(text)
        )
    } else {
        emptyList<ProductItemModel>()
    }


    private fun containsInNameOrDescrioption(text: String) = { product: ProductItemModel ->
        product.name.contains(
            text,
            ignoreCase = true,
        ) || product.description?.contains(
            text,
            ignoreCase = true,
        ) ?: false
    }


}

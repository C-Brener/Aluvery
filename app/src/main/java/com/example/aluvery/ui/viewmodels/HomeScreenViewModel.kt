package com.example.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleCandies
import com.example.aluvery.sampledata.sampleDrinks
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.ui.screens.homescreen.ItemsData
import com.example.aluvery.ui.states.HomeScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {
    private val productDao = ProductDao()

    private val _uiState: MutableStateFlow<HomeScreenUIState> =
        MutableStateFlow(HomeScreenUIState())
    val uiState: StateFlow<HomeScreenUIState> = _uiState.asStateFlow()


    init {
        _uiState.update { currentHomeScreenUIState ->
            currentHomeScreenUIState.copy(
                onSearchChange = {
                    _uiState.value =
                        _uiState.value.copy(textInput = it, searchProducts = searchedProducts(it))
                }
            )
        }
        viewModelScope.launch {
            productDao.getList().collect { products ->
                _uiState.value = _uiState.value.copy(
                    itemsData = getList(),
                    searchProducts = searchedProducts(_uiState.value.textInput)
                )
            }
        }
    }

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

    private fun searchedProducts(text: String): List<ProductItemModel> = if (text.isNotBlank()) {
        sampleProducts.filter(containsInNameOrDescription(text)) + productDao.getList().value.filter(
            containsInNameOrDescription(text)
        )
    } else {
        emptyList<ProductItemModel>()
    }

    fun findProducts() {
        _uiState.value = _uiState.value.copy(
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


//Quando trabalhamos com val, temos a capacidade de evitar que haja uma alteração na instãncia, ou seja, trabalhamos com a mesma instância, como valores internos sendo atualizados
// QUando estamos trabalhando com UIState, os mesmos precisam ser data class o que permite que possamos ter acesso a instância do .copy
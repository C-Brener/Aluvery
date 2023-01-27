package com.example.aluvery.ui.screens.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.aluvery.models.ProductItemModel

class HomeScreenUIState(private val itemsData: List<ItemsData>, searchedInitial: String = "") {
    var textInput by mutableStateOf(searchedInitial)
        private set

    fun isShowSections() = textInput.isBlank()

    fun searchedProducts(): List<ProductItemModel> {
        var list = mutableListOf<ProductItemModel>()
        itemsData.forEach {
            list = it.listItems.filter { productItem ->
                productItem.name.contains(
                    textInput,
                    ignoreCase = true
                ) || productItem.description?.contains(textInput) ?: false
            } as MutableList<ProductItemModel>
        }
        return list
    }

    val onSearchChange: (String) -> Unit = { searchText ->
        textInput = searchText
    }
}

package com.example.aluvery.ui.states

import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.screens.homescreen.ItemsData

data class HomeScreenUIState(
    val itemsData: List<ItemsData> = emptyList(),
    val textInput: String = "",
    val onSearchChange: (String) -> Unit = {},
    val searchProducts: List<ProductItemModel> = emptyList(),
) {
    fun isShowSections() = textInput.isBlank()

}

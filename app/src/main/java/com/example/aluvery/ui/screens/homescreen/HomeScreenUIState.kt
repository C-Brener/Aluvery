package com.example.aluvery.ui.screens.homescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.models.ProductItemModel

class HomeScreenUIState(
    private val itemsData: List<ItemsData>,
    searchedInitial: String = "",
) {

    var textInput by mutableStateOf(searchedInitial)
        private set

    var dao = ProductDao()

    fun isShowSections() = textInput.isBlank()

    fun searchedProducts(): List<ProductItemModel> {
        val newList = dao.getList().filter {
            filterSearch(it)
        } as MutableList<ProductItemModel>
        itemsData.forEach {
            newList.addAll(it.listItems.filter { filter -> filterSearch(filter) })
        }
        return newList
    }

    private fun filterSearch(productItem: ProductItemModel) =
        productItem.name.contains(
            textInput,
            ignoreCase = true
        ) || productItem.description?.contains(textInput) ?: false

    fun itemDataList(): List<ItemsData> {
        return itemsData
    }

    val onSearchChange: (String) -> Unit = { searchText ->
        textInput = searchText
    }
}

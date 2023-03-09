package com.example.aluvery.ui.screens.homescreen

import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.models.ProductItemModel

class HomeScreenUIState(
    private val itemsData: List<ItemsData> = emptyList(),
    val textInput: String = "",
    val onSearchChange: (String) -> Unit = {},
) {
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
        ) || productItem.description?.contains(textInput, ignoreCase = true) ?: false

    fun itemDataList(): List<ItemsData> {
        return itemsData
    }

}

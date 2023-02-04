package com.example.aluvery.ui.screens.productformscreen

import com.example.aluvery.models.ProductItemModel

data class ProductFormScreenUitState(
    val name: String = "",
    val url: String = "",
    val price: String = "",
    val description: String = "",
    val isError: Boolean = false,
    val isTypeSelected: String = "",
    val onNameChange: (String) -> Unit = {},
    val onUrlChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onError: (Boolean) -> Unit = {},
    val isTypeSelectedChanged: (String) -> Unit = {},
    val saveProduct: (ProductItemModel) -> Unit = {}
)
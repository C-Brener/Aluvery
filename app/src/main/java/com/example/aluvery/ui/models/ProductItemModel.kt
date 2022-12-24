package com.example.aluvery.ui.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import java.math.BigDecimal

data class ProductItemModel(
    val productName:String,
    val productValue:BigDecimal,
    val productImage:Int,
    val withDescription:Boolean = false,
    val description:String = LoremIpsum(50).values.first()
)

data class ProductSectionModel(
    val productSection:String,
    val productListItem:List<ProductItemModel>
)
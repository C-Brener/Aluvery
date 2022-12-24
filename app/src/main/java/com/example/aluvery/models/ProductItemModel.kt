package com.example.aluvery.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import java.math.BigDecimal

data class ProductItemModel(
    val productName:String,
    val productValue:BigDecimal,
    val productImage:Int,
    val withDescription:Boolean = false,
    val description:String = LoremIpsum(50).values.first()
)


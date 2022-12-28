package com.example.aluvery.models

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import java.math.BigDecimal

class ProductItemModel(
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val withDescription:Boolean = false,
    val description:String = LoremIpsum(50).values.first()
)
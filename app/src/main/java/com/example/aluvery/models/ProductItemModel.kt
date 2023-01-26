package com.example.aluvery.models

import java.math.BigDecimal

class ProductItemModel(
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val withDescription: Boolean = false,
    val description: String? = null,
    val typeProduct: String? = null
)
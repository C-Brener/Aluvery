package com.example.aluvery.sampledata

import com.example.aluvery.R
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.models.ProductSectionModel
import java.math.BigDecimal

val listItensSalgados = listOf(
    ProductItemModel("Hamburguer", BigDecimal(15.99), R.drawable.burger),
    ProductItemModel("Pizza", BigDecimal(45.99), R.drawable.pizza),
    ProductItemModel("Batata Frita", BigDecimal(5.99), R.drawable.fries),
)
val listItensDoces = listOf(
    ProductItemModel("Brigadeiro", BigDecimal(15.99), R.drawable.placeholder),
    ProductItemModel("Mousse", BigDecimal(45.99), R.drawable.placeholder),
    ProductItemModel("Brownie", BigDecimal(5.99), R.drawable.placeholder),
)
val listProductSection = listOf(
    ProductSectionModel(
        productSection = "Salgados",
        productListItem = listItensSalgados
    ), ProductSectionModel(
        productSection = "Salgados",
        productListItem = listItensSalgados
    ),
    ProductSectionModel(
        productSection = "Doces",
        productListItem = listItensDoces
    )
)

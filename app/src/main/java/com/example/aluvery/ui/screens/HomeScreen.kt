package com.example.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.aluvery.R
import com.example.aluvery.ui.components.ProductSection
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.models.ProductSectionModel
import com.example.aluvery.sampledata.listProductSection
import java.math.BigDecimal


@Composable
fun HomeScreen() {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        listProductSection.forEach { productSection ->
            ProductSection(
                listItens = productSection.productListItem,
                sectionTitle = productSection.productSection
            )
        }
    }
}
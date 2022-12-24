package com.example.aluvery.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.models.ProductItemModel


@Composable
fun ProductSection(listItens: List<ProductItemModel>, sectionTitle: String) {
    Column {
        Text(
            text = sectionTitle,
            fontSize = 20.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(
                16.dp
            )
        )
        Row(
            modifier = Modifier
                .horizontalScroll(state = rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            listItens.forEach {
                Spacer(modifier = Modifier)
                ProductItem(it.withDescription, it)
                Spacer(modifier = Modifier)
            }

        }
    }
}

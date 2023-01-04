package com.example.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.aluvery.extensions.toConverterBrazilianCurrency
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.sampledata.sampleProducts
import com.example.aluvery.ui.theme.AluveryTheme


@Composable
fun CardProductItem(
    product: ProductItemModel,
    elevation: Dp = 4.dp
) {
    Card(
        Modifier
            .fillMaxWidth()
            .heightIn(150.dp),
        elevation = elevation
    ) {
        Column {
            SubcomposeAsyncImage(
                model = product.image,
                loading = { CircularProgressIndicator(color = Color.Red) },
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primaryVariant)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toConverterBrazilianCurrency()
                )
            }
            // TODO: adicionar descrição do produto
            // Text(
            //     text = product.description,
            //     Modifier
            //         .padding(16.dp)
            // )
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = sampleProducts.random(),
            )
        }
    }
}
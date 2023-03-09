package com.example.aluvery.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.aluvery.extensions.toConverterBrazilianCurrency
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal


@Composable
fun CardProductItem(
    product: ProductItemModel,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    isExpanded: Boolean = false
) {
    var expanded by remember {
        mutableStateOf(isExpanded)
    }
    Card(
        Modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable { expanded = !expanded }
            .then(modifier),
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
            product.description?.let {
                AnimatedVisibility(visible = expanded) {
                    Text(
                        text = it,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = ProductItemModel(
                    name = "Teste name",
                    price = BigDecimal(15.99)
                )
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemWithDescriptionPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = ProductItemModel(
                    name = "Teste name",
                    price = BigDecimal(15.99),
                    description = LoremIpsum(50).values.first()
                ),
                isExpanded = true
            )
        }
    }
}
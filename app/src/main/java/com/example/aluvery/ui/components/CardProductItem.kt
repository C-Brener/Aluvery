package com.example.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
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
    elevation: Dp = 4.dp
) {
    var expandend by remember {
        mutableStateOf(false)
    }
    Card(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp)
            .heightIn(150.dp)
            .clickable { expandend = !expandend }
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
                Text(
                    text = it,
                    Modifier
                        .padding(16.dp),
                    maxLines = if (expandend) Int.MAX_VALUE else 2,
                    overflow = if (expandend) TextOverflow.Clip else TextOverflow.Ellipsis,
                )
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
                )
            )
        }
    }
}
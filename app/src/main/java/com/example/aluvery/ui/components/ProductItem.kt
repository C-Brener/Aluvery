package com.example.aluvery.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.aluvery.R
import com.example.aluvery.extensions.toConverterBrazilianCurrency
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.theme.AluveryTheme
import com.example.aluvery.ui.theme.Indigo400
import com.example.aluvery.ui.theme.Indigo500
import java.math.BigDecimal

@Composable
fun ProductItem(product: ProductItemModel, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        elevation = 4.dp
    ) {
        Column(
            Modifier
                .heightIn(250.dp, 300.dp)
                .width(200.dp)
        ) {
            val imageSize = 100.dp
            Box(
                modifier = Modifier
                    .height(imageSize)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colors.primary,
                                MaterialTheme.colors.secondary
                            )
                        )
                    )
                    .fillMaxWidth()
            ) {
                SubcomposeAsyncImage(
                    model = product.image,
                    loading = { CircularProgressIndicator(color = Color.Red) },
                    contentDescription = null,
                    modifier = Modifier
                        .size(imageSize)
                        .offset(y = imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(imageSize / 2))
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price.toConverterBrazilianCurrency(),
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    AluveryTheme {
        Surface {
            ProductItem(
                ProductItemModel(
                    name = LoremIpsum(50).values.first(),
                    price = BigDecimal("14.99")
                )
            )
        }
    }
}

@Composable
fun ProductItemWithDescription(content: ProductItemModel) {
    Surface(elevation = 10.dp, shape = RoundedCornerShape(15.dp)) {
        Column(
            modifier = Modifier
                .height(250.dp)
                .widthIn(200.dp, 210.dp)
                .verticalScroll(rememberScrollState())

        ) {
            val size = 100.dp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Indigo500,
                                Indigo400
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(size)
                        .offset(y = size / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(size / 2))
            Column() {
                Text(
                    text = content.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = content.price.toConverterBrazilianCurrency(),

                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .background(Indigo500)
                        .fillMaxWidth()
                ) {
                    content.description?.let {
                        Text(
                            text = it,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }

    }
}


@Composable
fun ProductItemWithoutDescription(content: ProductItemModel) {
    Surface(elevation = 10.dp, shape = RoundedCornerShape(15.dp)) {
        Column(
            modifier = Modifier
                .heightIn(250.dp, 300.dp)
                .widthIn(200.dp, 210.dp)

        ) {
            val size = 100.dp
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(size)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Indigo500,
                                Indigo400
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .size(size)
                        .offset(y = size / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(size / 2))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = content.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,

                    )
                Text(
                    text = content.price.toConverterBrazilianCurrency(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}


// ---------- Coil ----------

// O Coil é uma lib que trablha com o Kotlin para rederização de imagens fazendo requisção atráves da internet.
// https://coil-kt.github.io/coil/compose/
// O Coil como todo tem uma pagina espcifica para  jetpack compose
// Para que o coil funcione corretamente precisamos implementar sua dependência em nosso projeto, precisamos declarar no android manifest o acesso a internet e por fim utilizar o composable especificio do coil
// A utiliuzação do coil nos ajuda a flexibilizar o carregamento de imagens e pular etapas de requições um tanto complexas
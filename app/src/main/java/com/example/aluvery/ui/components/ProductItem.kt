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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.extensions.toConverterBrazilianCurrency
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.theme.Purple500
import com.example.aluvery.ui.theme.Teal200

@Composable
fun ProductItem(withDescription: Boolean = false, content: ProductItemModel) {
    if (withDescription) {
        ProductItemWithDescription(content)

    } else {
        ProductItemWithoutDescription(content = content)
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
                                Purple500,
                                Teal200
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = content.productImage),
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
                    text = content.productName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Text(
                    text = content.productValue.toConverterBrazilianCurrency(),

                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .background(Purple500)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = content.description,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
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
                                Purple500,
                                Teal200
                            )
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = content.productImage),
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
                    text = content.productName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,

                    )
                Text(
                    text = content.productValue.toConverterBrazilianCurrency(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}
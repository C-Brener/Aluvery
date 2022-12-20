package com.example.aluvery.ui.components.challengers

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.aluvery.R
import com.example.aluvery.ui.theme.Purple200
import com.example.aluvery.ui.theme.Purple500
import com.example.aluvery.ui.theme.Purple700
import com.example.aluvery.ui.theme.Teal200


@Composable
fun CardChallenge() {
    Surface(elevation = 10.dp, shape = RoundedCornerShape(15.dp)) {
        Row(
            Modifier
                .widthIn(400.dp, 410.dp)
                .height(200.dp)
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .fillMaxHeight()
                    .background(
                        Brush.verticalGradient(
                            listOf(Purple700, Purple200)
                        )
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .offset((60).dp)
                        .clip(CircleShape)
                        .align(Alignment.Center)
                        .border(
                            border = BorderStroke(
                                width = 2.dp, brush = Brush.horizontalGradient(
                                    colors = listOf(Purple500, Teal200)
                                )
                            ),
                            shape = CircleShape
                        )
                )
            }
            Spacer(modifier = Modifier.width(100.dp))
            Row(Modifier.fillMaxHeight()) {
                Text(
                    text = LoremIpsum(70).values.first(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(20.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview
@Composable
fun CardChallengePreview() {
    CardChallenge()
}
package com.example.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.ui.theme.AluveryTheme
import com.example.aluvery.ui.theme.Purple500
import com.example.aluvery.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme() {
                Surface() {
                    ProductItem()
                }
            }
        }
    }
}

@Composable
fun ProductItem() {
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(250.dp)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(brush = Brush.horizontalGradient(colors = listOf(Purple500, Teal200)))
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .offset(y = 50.dp)
                    .clip(shape = CircleShape)
                    .align(Alignment.BottomCenter)
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = LoremIpsum(50).values.first(),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,

                )
            Text(text = "R$14.99", fontSize = 14.sp, fontWeight = FontWeight(400), modifier = Modifier.padding(top=8.dp))
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem()
}

//---------- Modifiers ----------
// Existem algumas extensões de modifiers que só podem ser usadas em contextos especificos, por exemplo, Modifier.align é um modifier
// que é utilizado em contextos de row ou column, isso porquê, esses modifeirs precisam de uma orientação para funcionar o seu alinhamento.
// clip -> Serve para fazer cortes na forma que desejammos, no caso do exemplo acima desejavamos um circulo . https://foso.github.io/Jetpack-Compose-Playground/cookbook/how_to_create_custom_shape/
// offset -> É um modifier de deslocamento, serve para deslocar um componente baseando-se no plano cartesiano. Quando o offset altera a posição de um item,
// o mesmo continua ocupando o espaçamento da posição inicial, ou seja, é sempre bom utilizar o offset em locais que não acabe ferindo  o layout

// --------- Text ----------
// O text é um componente que possui algumas propriedades que não estão inseridas dentro da interface do modifier, sendo assim






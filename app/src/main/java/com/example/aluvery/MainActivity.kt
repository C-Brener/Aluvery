package com.example.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
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
                Surface(modifier = Modifier.background(Color.Gray)) {
                    ProductSection()
                }
            }
        }
    }
}

@Composable
fun ProductSection() {
    Column {
        Text(
            text = "Title",
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
            Spacer(modifier = Modifier)
            ProductItem(content = LoremIpsum(50).values.first(), withDescription = true)
            ProductItem()
            ProductItem()
            Spacer(modifier = Modifier)
        }
    }
}


@Composable
fun ProductItem(withDescription: Boolean = false, content: String? = null) {
    if (withDescription) {
        if (content != null) {
            ProductItemWithDescription(content)
        }
    } else {
        ProductItemWithoutDescription()
    }
}

@Composable
fun ProductItemWithDescription(content: String) {
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
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier
                        .size(size)
                        .offset(y = size / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.BottomCenter)
                )
            }
            Spacer(modifier = Modifier.height(size / 2))
            Column() {
                Text(
                    text = LoremIpsum(50).values.first(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 16.dp)
                    )
                Text(
                    text = "R$14.99",

                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp)
                )
                Column(modifier = Modifier
                    .padding(top = 10.dp)
                    .background(Purple500)
                    .fillMaxWidth()) {
                    Text(text = content, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }

    }
}


@Composable
fun ProductItemWithoutDescription() {
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
                    painter = painterResource(id = R.drawable.ic_launcher_background),
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
                    text = LoremIpsum(50).values.first(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,

                    )
                Text(
                    text = "R$14.99",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400),
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ProductItemPreview() {
    ProductItem()
}

@Preview(showSystemUi = true)
@Composable
fun ProductSectionPreview() {
    ProductSection()
}

//---------- Modifiers ----------
// Existem algumas extensões de modifiers que só podem ser usadas em contextos especificos, por exemplo, Modifier.align é um modifier
// que é utilizado em contextos de row ou column, isso porquê, esses modifeirs precisam de uma orientação para funcionar o seu alinhamento.

// clip -> Serve para fazer cortes na forma que desejammos, no caso do exemplo acima desejavamos um circulo . https://foso.github.io/Jetpack-Compose-Playground/cookbook/how_to_create_custom_shape/

// offset -> É um modifier de deslocamento, serve para deslocar um componente baseando-se no plano cartesiano. Quando o offset altera a posição de um item,
// o mesmo continua ocupando o espaçamento da posição inicial, ou seja, é sempre bom utilizar o offset em locais que não acabe ferindo  o layout


//widthIn & heigthIn define uma interseção de valores que podem ser atingidos para esses parametros, sendo o primeiro valor o minimo e o segundo valor o maximo
// Quando trabalhamos com componentes externos, é sempre viável fazer com que o nosso composable seja alterado ma hora de sua
// chamada por quem estar o chamando, isso porque ele acaba mantendo o comportamento original e sendo alterado pelo pai, exemplo, não devemos definir paddings em nossos composables


//ROW
// QUando trabalhamos com row ou com o Columns estamos denro do escopo desse componente em questão, por conta desse escopo temos acesso alguns tipos de propriedades,
// dentre essas propriedades podemos citar a arragenment, que é uma especie de organizaçã dos componentes que estão dentro do , o arrragmente tme alguns comportamentos default, contudo, iremos utilizar o spacedBy que podemos definir o espeçamento que desejamos entre os componentes
// Dentre os modifiers do Row temos o horizontalScrollable que nos dá a capacidade de ter um scrollamento no componente de row, podemos utiliZaR NO PARÂMETRO DE STATE:
//ScrollState -> Definimos o ponto inicil doo scroll em pixels, geralmente definido como 0
// rememberScrollState -> Como o jetpack compose trabalha muito com estados temos o remeemberScroll que define o estado de scrollamento e também persiste a posição em que paramos do scroll mesmo que haja recmoposição de tela.


// --------- Text ----------
// O text é um componente que possui algumas propriedades que não estão inseridas dentro da interface do modifier, sendo assim





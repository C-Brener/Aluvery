package com.example.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.aluvery.ui.screens.HomeScreen
import com.example.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App() {
    AluveryTheme() {
        Surface(modifier = Modifier.background(Color.Gray)) {
            HomeScreen()
        }
    }
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





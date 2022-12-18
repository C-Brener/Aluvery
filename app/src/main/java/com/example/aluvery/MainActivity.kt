package com.example.aluvery

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme() {
                Surface() {
                    MyFirstComposable()

                }
            }
        }
    }

}

@Composable
fun MyFirstComposable() {
    Text(text = "Meu Primeiro Texto")
    Text(text = "Meu segundo texto maior")
}

@Preview(showBackground = true, widthDp = 300, heightDp = 300)
@Preview(showSystemUi = true, name = "TextPreview", uiMode = UI_MODE_NIGHT_YES)
@Preview(showSystemUi = true, name = "TextPreview", uiMode = UI_MODE_NIGHT_NO)
@Composable
fun MyFirstComposablePreview() {
    AluveryTheme {
        Surface() {
            MyFirstComposable()

        }
    }
}


// O compose trabalha de forma declarativa, ou seja, não temos tão presente os getterse setters,
// contudo, para a mudança de comportamento das telas usamos a recomposição que redesenhará a tela
// no momento em que o estado relacionado a aquele componente tenha alguma alteração.

// O setContent é onde setáremos o nosso conteudo, ele recebe uma função como parâmetros e conteudo que é um composable

//Separamos o Preview Dos composables para questões organizacionais

// Digitando "comp" no android studio é um atalho que cria um composable para nós

//Digitando "prev" no android studio criamos um composable com preview
// É possível fazer mais de um preview em um unico composable

//Sempre que criamos um app com compose ele cria um Theme e um Surface que pega informação
// de design diretamente do Material do google facilitando nossa vida.

// Quando se trata de preview temos algumas notações que nos ajudam em questão de visualização
// como o showSistemUI que implementa uma tela similar a do emulador nos fazendo ter uma visão
// ampla de como o composable se comportaria em tela.


package com.example.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aluvery.ui.theme.AluveryTheme

@Composable
fun MyFirstComposable() {
    Text(text = "Meu Primeiro Texto")
    Text(text = "Meu segundo texto maior")
}

//@Preview(showBackground = true, widthDp = 300, heightDp = 300)
//@Preview(showSystemUi = true, name = "TextPreview", uiMode = UI_MODE_NIGHT_YES)
//@Preview(showSystemUi = true, name = "TextPreview", uiMode = UI_MODE_NIGHT_NO)
@Preview
@Composable
fun MyFirstComposablePreview() {
    AluveryTheme {
        Surface() {
            MyFirstComposable()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColumnPreview() {
    Column() {
        Text(text = "Composable de coluna")
        Text(text = "Original Android")
    }
}

@Preview(showBackground = true)
@Composable
fun RowPreview() {
    Row {
        Text(text = "Composable de row")
        Text(text = "Original Android")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxPreview() {
    Box {
        Text(text = "Composable de box")
        Text(text = " Original android")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyComposableLayoutPreview() {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .background(Color.Cyan)
            .fillMaxSize()

    ) {
        Column(
            Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .background(color = Color.Red)
        ) {
            Text(text = "Testando diversos layouts")
            Text(text = "Com composables padrões")
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .background(Color.Green)
                    .fillMaxHeight(0.1f)
            ) {
                Text(text = "Do Próprio")
                Text(text = " Jetpack compose")
            }
        }

    }

}


// O compose trabalha de forma declarativa, ou seja, não temos tão presente os getterse setters,
// contudo, para a mudança de comportamento das telas usamos a recomposição que redesenhará a tela
// no momento em que o estado relacionado a aquele componente tenha alguma alteração.
// O setContent é onde setaremos o nosso conteudo, ele recebe uma função como parâmetros e conteudo que é um composable


// --------------- Atalhos --------------------------
// Digitando "comp" no android studio é um atalho que cria um composable para nós
//Digitando "prev" no android studio criamos um composable com preview


// ---------------------- Preview --------------------------------
//Separamos o Preview Dos composables para questões organizacionais
// É possível fazer mais de um preview em um unico composable, quando utilizamos a
// anotação "preview" estamos criando um preview exclusivo ou seja, se usamos mais de um
// preview, criamos mais de um preview
//Sempre que criamos um app com compose ele cria um Theme e um Surface que pega informação
// de design diretamente do Material do google facilitando nossa vida.
// Quando se trata de preview temos algumas notações que nos ajudam em questão de visualização
// como o showSystemUI que implementa uma tela similar a do emulador nos fazendo ter uma visão
// ampla de como o composable se comportaria em tela.

// ----------------------- Componentes de Layout ------------------------
// Row -> Os componentes dentro do Row é organizado horizontal
// Column -> Os componentes dentro do Column é organizado de forma vertical
// Box -> Os componentes dentro do Box ficam um sobre o outro.

// ------------- Modifiers ---------------
// É uma interface que é implementada nos composes que nos dar o poder de personalizar nossos composables
//A ordem da chamada do modifier de estilização importa, isso porquê a depender da chamada o comportamento pode ser diferente


// Estudar builder pattern


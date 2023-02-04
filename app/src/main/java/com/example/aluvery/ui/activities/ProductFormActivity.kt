package com.example.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.ui.screens.productformscreen.ProductFormScreenStateFul
import com.example.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreenStateFul(){
                        finish()
                    }
                }
            }
        }
    }
}


//# Espaçamento
//Dentro do Column temos uma propertie chamada arrangment que server para definir um
// espaçamento no conteudo invocado dentro do escopo do column
//heightIn → Define altura minima, não limitando a altura de um componente e sim, apenasindicando qual seria sua altura inicial, ou seja, o componente ainda podera expandir.

// O try catch server para tentar capturar uma informação e caso não consiga ele
// "pega o erro" e trata o mesmo, no caso acima sabemos que
// o error é um "NumberFormatException" logo ele
// trata esse erro da melhor forma que seria convertendo o erro
// pra 0 pra não quebrar a aplicação


//O Scroll só acontece quando a alteração é diretamente no
// conteudo do compose e ultrapassa a nossa visualização,ou seja, caso o tamanho do composable
// ultrapasse a tela, agora, caso seja uma ultrapassagem de
// entrada, por criação de um novo conteudo, e abertura e teclado, issso não é identificado
// pra isso precisamos alterar o manifest adicionando o parametro
// "android:windowSoftInputMode="adjustResize"" dentro da activity que desejamos o comportamento de scroll na entrada

// Tipos de teclados

// Os tipos de teclados são extremamaente importantes quando estamos lidando com input do usuário, pois isso pode evitar que os mesmo causem erros na aplicação, como por exemplo:
// EX: Adicionar texto num campo só de numero.
// Escolher o tipo de teclado influencia nessas questões, dito isso podemos simplesmente no composable de TextField algumas configurações impedindo esses erros:

//        TextField(modifier = Modifier.fillMaxWidth(), value = price, onValueChange = {
//            price = it
//        }, label = { Text(text = "PRICE") },
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Decimal,
//                imeAction = ImeAction.Next,
//            ))
// o ime -> "Input method Editor" seria a ação do botão inferior direto do teclado que pode ter varios metodos e utilizações

//É possível filtrar o texto de acordo com o que desejamos, como no exemplo do commit"numero do commit" onde criamos um formatter
// para definir qual seria o valor padrão aceito pelo input de preço, podemos manipular o onValueChange de diversas formas e essa é uma delas
//https://www.alura.com.br/conteudo/expressoes-regulares -> Expressões regulares.

//Scaffold -> É um componente default do compose que nos dar algumas habilidades para usar o
// componente do material, o scaffold como permite diversas manipulações e adicção de componentens
// dentro de si mesmo, mas para que possamos utiliza-lo precisamos adicionar como parâmetro do que foi chamado dentro dele o seu padding values.


// O compose trabalha com chamadas de estados, logo se faz necessário que evitemos que o composable lide com chamadas de sistems, tipo a
// por intent feita nesse commit, é melhor que indiquemos que houve um evento e quem chamou o composable
// em questão faça a chamada do contexto e a mudança de acordo com o , para isso podemos elevar a ação caso seja um botão para que o responsável por chamar esse composable decida o que fara com o clique

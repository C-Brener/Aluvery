package com.example.aluvery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.aluvery.R
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormActivity : ComponentActivity() {

    val saveProduct = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen(saveProduct = { productItem ->
                        saveProduct.save(productItem)
                        finish()
                    })
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen(saveProduct: (ProductItemModel) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        var url by remember {
            mutableStateOf("")
        }
        var price by remember {
            mutableStateOf("")
        }
        var name by remember {
            mutableStateOf("")
        }
        var description by remember {
            mutableStateOf("")
        }
        var formatter by remember {
            mutableStateOf(DecimalFormat("#.##"))
        }
        var isError by remember {
            mutableStateOf(false)
        }
        var isTypeSelected by remember {
            mutableStateOf("Todos os produtos")
        }
        Text(modifier = Modifier.fillMaxWidth(), text = "Criando Produto", fontSize = 28.sp)
        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
            )

        }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = url,
            onValueChange = {
                url = it
            },
            label = { Text(text = "URL") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            label = { Text(text = "NAME") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        TextField(modifier = Modifier.fillMaxWidth(), value = price, onValueChange = {
            isError = try {
                price = formatter.format(BigDecimal(it))
                false
            } catch (e: IllegalArgumentException) {
                if (it.isBlank()) {
                    price = it
                }
                true
            }
        }, label = { Text(text = "PRICE") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next,
            ), isError = isError
        )
        if (isError) {
            TextError()
        }
        TextField(modifier = Modifier
            .fillMaxWidth()
            .heightIn(100.dp), value = description, onValueChange = {
            description = it
        }, label = { Text(text = "DESCRIPTION") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        DropDownMenu() {
            isTypeSelected = it
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal(0)
            }
            val newProduct = ProductItemModel(
                name = name,
                image = url,
                price = convertedPrice,
                description = description,
                withDescription = true,
                typeProduct = isTypeSelected
            )
            saveProduct(newProduct)
            Log.i("Teste", "ProductFormScreen: ${newProduct.price}")
        }) {
            Text(text = "Criar produto")
        }
    }
}

@Composable
fun TextError() {
    Text(
        text = "O padrão de inserção é \"5.00\", tente novamente",
        color = Color.Red,
        fontSize = 12.sp
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(ontItemSelected: (String) -> Unit = {}) {
    val listItems = arrayOf("Todos os produtos", "Doces", "Bebidas")

    var selectedItem by remember {
        mutableStateOf(listItems[0])
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        TextField(
            value = selectedItem,
            onValueChange = {},
            readOnly = true,
            label = { Text(text = "Tipo de Produto") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            listItems.forEach { selectedOption ->
                // menu item
                DropdownMenuItem(onClick = {
                    ontItemSelected(selectedOption)
                    selectedItem = selectedOption
                    expanded = false
                }) {
                    Text(text = selectedOption)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen()
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

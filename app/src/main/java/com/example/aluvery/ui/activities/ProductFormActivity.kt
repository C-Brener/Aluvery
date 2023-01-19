package com.example.aluvery.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

class ProductFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
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
        Text(modifier = Modifier.fillMaxWidth(), text = "Criando Produto", fontSize = 28.sp)

        TextField(modifier = Modifier.fillMaxWidth(), value = url, onValueChange = {
            url = it
        }, label = { Text(text = "URL") })
        TextField(modifier = Modifier.fillMaxWidth(), value = name, onValueChange = {
            name = it
        }, label = { Text(text = "NAME") })
        TextField(modifier = Modifier.fillMaxWidth(), value = price, onValueChange = {
            price = it
        }, label = { Text(text = "PRICE") })
        TextField(modifier = Modifier
            .fillMaxWidth()
            .heightIn(100.dp), value = description, onValueChange = {
            description = it
        }, label = { Text(text = "DESCRIPTION") })
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
                withDescription = true
            )
            Log.i("Teste", "ProductFormScreen: ${newProduct.price}")
        }) {
            Text(text = "Criar produto")
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
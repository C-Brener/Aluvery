package com.example.aluvery.ui.screens.productformscreen

import android.util.Log
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
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal
import java.text.DecimalFormat

@Composable
fun ProductFormScreen(
    saveProduct: (ProductItemModel) -> Unit = {},
    state: ProductFormScreenUitState,
) {
    var url by remember {
        mutableStateOf("")
    }
    var price by remember {
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
            value = state.name,
            onValueChange = {
                state.onSearchChange(it)
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
                name = state.name,
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
            ProductFormScreen(state = ProductFormScreenUitState("", onSearchChange = {}))
        }
    }
}
package com.example.aluvery.ui.screens.productformscreen

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

@Composable
fun ProductFormScreenStateFul(
    onFinishScreen: (Boolean) -> Unit = {}
) {
    val saveProduct = ProductDao()

    var textName by remember {
        mutableStateOf("")
    }
    var url by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var isTypeSelected by remember {
        mutableStateOf("")
    }
    var isError by remember {
        mutableStateOf(false)
    }
    val state =
        remember(textName, url, price, description, isTypeSelected, isError) {
            ProductFormScreenUitState(
                name = textName,
                url = url,
                price = price,
                isError = isError,
                description = description,
                isTypeSelected = isTypeSelected,
                onError = { isError = it },
                isTypeSelectedChanged = { isTypeSelected = it },
                onPriceChange = { price = it },
                onUrlChange = { url = it },
                onNameChange = { textName = it },
                onDescriptionChange = { description = it },
                saveProduct = {
                    saveProduct.save(it)
                    onFinishScreen(true)
                }
            )
        }
    val formatter by remember {
        mutableStateOf(DecimalFormat("#.##"))
    }

    ProductFormScreenStateLess(state = state, formatter = formatter)

}

@Composable
fun ProductFormScreenStateLess(state: ProductFormScreenUitState, formatter: DecimalFormat) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(modifier = Modifier.fillMaxWidth(), text = "Criando Produto", fontSize = 28.sp)
        if (state.url.isNotBlank()) {
            AsyncImage(
                model = state.url,
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
            value = state.url,
            onValueChange = {
                state.onUrlChange(it)
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
                state.onNameChange(it)

            },
            label = { Text(text = "NAME") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        TextField(modifier = Modifier.fillMaxWidth(), value = state.price, onValueChange = {
            state.onError(
                try {
                    state.onPriceChange(formatter.format(BigDecimal(it)))
                    false
                } catch (e: IllegalArgumentException) {
                    if (it.isBlank()) {
                        state.onPriceChange(it)
                    }
                    true
                }
            )
        }, label = { Text(text = "PRICE") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next,
            ), isError = state.isError
        )
        if (state.isError) {
            TextError()
        }
        TextField(modifier = Modifier
            .fillMaxWidth()
            .heightIn(100.dp), value = state.description, onValueChange = {
            state.onDescriptionChange(it)
        }, label = { Text(text = "DESCRIPTION") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
        DropDownMenu() {
            state.isTypeSelectedChanged(it)
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            val convertedPrice = try {
                BigDecimal(state.price)
            } catch (e: NumberFormatException) {
                BigDecimal(0)
            }
            val newProduct = ProductItemModel(
                name = state.name,
                image = state.url,
                price = convertedPrice,
                description = state.description,
                withDescription = true,
                typeProduct = state.isTypeSelected
            )
            state.saveProduct(newProduct)
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
            ProductFormScreenStateFul()
        }
    }
}
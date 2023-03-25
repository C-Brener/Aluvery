package com.example.aluvery.ui.screens.productformscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.example.aluvery.ui.theme.AluveryTheme
import com.example.aluvery.ui.viewmodels.ProductFormScreenViewModel

@Composable
fun ProductFormScreenStateFul(
    viewModel: ProductFormScreenViewModel,
    onFinishScreen: (Boolean) -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()

    ProductFormScreenStateLess(state = state) {
        viewModel.save()
        onFinishScreen(true)
    }
}

@Composable
fun ProductFormScreenStateLess(state: ProductFormScreenUitState, onSaveClick: () -> Unit) {
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
            state.onPriceChange(it)
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
        Button(modifier = Modifier.fillMaxWidth(), onClick = onSaveClick) {
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
            ProductFormScreenStateFul(viewModel = ProductFormScreenViewModel())
        }
    }
}
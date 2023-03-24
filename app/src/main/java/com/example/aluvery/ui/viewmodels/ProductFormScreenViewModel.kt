package com.example.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.aluvery.ui.screens.productformscreen.ProductFormScreenUitState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<ProductFormScreenUitState> =
        MutableStateFlow(ProductFormScreenUitState())
    val uiState: StateFlow<ProductFormScreenUitState> = _uiState.asStateFlow()

    val formatter =
        DecimalFormat("#.##")


    init {
        _uiState.update { currentProductFormScreenUIState ->
            currentProductFormScreenUIState.copy(
                onUrlChange = {
                    _uiState.value =
                        _uiState.value.copy(url = it)
                },
                onNameChange = {
                    _uiState.value =
                        _uiState.value.copy(name = it)
                },
                onDescriptionChange = {
                    _uiState.value =
                        _uiState.value.copy(description = it)
                },
                onError = {
                    _uiState.value =
                        _uiState.value.copy(isError = it)
                },
                onPriceChange = {
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty()) {
                            it
                        } else {
                            null
                        }
                    }
                    price?.let {
                        _uiState.value =
                            _uiState.value.copy(price = price)
                    }
                }
            )
        }

    }

}
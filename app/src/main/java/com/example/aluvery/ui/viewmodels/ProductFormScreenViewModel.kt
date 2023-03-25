package com.example.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.aluvery.data.dao.ProductDao
import com.example.aluvery.models.ProductItemModel
import com.example.aluvery.ui.screens.productformscreen.ProductFormScreenUitState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenViewModel : ViewModel() {
    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<ProductFormScreenUitState> =
        MutableStateFlow(ProductFormScreenUitState())
    val uiState: StateFlow<ProductFormScreenUitState> = _uiState.asStateFlow()

    private val formatter =
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
                },
                isTypeSelectedChanged = {
                    _uiState.value =
                        _uiState.value.copy(isTypeSelected = it)
                }
            )
        }

    }

    fun save() {
        _uiState.value.run {
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
            dao.save(newProduct)
        }
    }


}
package com.example.aluvery.data.dao

import androidx.compose.runtime.mutableStateListOf
import com.example.aluvery.models.ProductItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {

    companion object {
        private val listProduct = MutableStateFlow<List<ProductItemModel>>(emptyList())
    }

    fun getList() = listProduct.asStateFlow()
    fun save(product: ProductItemModel) {
        listProduct.value = listProduct.value + product
    }
}

// DAO (Data Acess Objects) -> São objetos responsáveis pelo acesso e consulta
// informaçãoes do banco de dados SQLite
// A melhor forma de utilizar o state flow que vai ser escutado fora do DAO é utilizando-o como asStateFlow, isso porquê dessa forma não é possível fazer o casting para mutable
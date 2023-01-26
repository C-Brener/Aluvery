package com.example.aluvery.data.dao

import androidx.compose.runtime.mutableStateListOf
import com.example.aluvery.models.ProductItemModel

class ProductDao {

    companion object {
        private val listProduct = mutableStateListOf<ProductItemModel>()
    }

    fun getList() = listProduct.toList()
    fun save(product: ProductItemModel) {
        listProduct.add(product)
    }
}

// DAO (Data Acess Objects) -> São objetos responsáveis pelo acesso e consulta
// informaçãoes do banco de dados SQLite

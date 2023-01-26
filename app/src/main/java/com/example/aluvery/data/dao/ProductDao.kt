package com.example.aluvery.data.dao

import com.example.aluvery.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val listproduct = sampleProducts.toMutableList()
    }

    fun getList() = listproduct.toList()
}

// DAO (Data Acess Objects) -> São objetos responsáveis pelo acesso e consulta
// informaçãoes do banco de dados SQLite

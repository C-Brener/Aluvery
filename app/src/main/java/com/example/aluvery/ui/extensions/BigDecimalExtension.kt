package com.example.aluvery.ui.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toConverterBrazilianCurrency():String{
    return NumberFormat.getCurrencyInstance(Locale("pt","br")).format(this)
}
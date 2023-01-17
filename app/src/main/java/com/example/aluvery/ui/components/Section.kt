package com.example.aluvery.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Section(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier) {
        title()
        Spacer(modifier = Modifier.height(16.dp))
        content()
    }
}
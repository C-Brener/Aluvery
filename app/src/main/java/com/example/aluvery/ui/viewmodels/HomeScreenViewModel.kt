package com.example.aluvery.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.aluvery.ui.screens.homescreen.HomeScreenUIState

class HomeScreenViewModel: ViewModel() {

    var uitState = mutableStateOf(HomeScreenUIState())
        private set


}
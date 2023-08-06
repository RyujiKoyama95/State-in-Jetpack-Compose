package com.example.stateinjetpackcompose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WellnessTask(
    val id: Int,
    val label: String,
    // Todo: 修正必要
    var isChecked: MutableState<Boolean> = mutableStateOf(false)
)
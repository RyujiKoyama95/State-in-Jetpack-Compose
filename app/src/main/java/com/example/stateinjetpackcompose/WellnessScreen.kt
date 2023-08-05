package com.example.stateinjetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        StatefulCounter()
        Spacer(modifier = Modifier.padding(10.dp))
        WellnessTasksList()
    }
}
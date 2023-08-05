package com.example.stateinjetpackcompose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask>
) {
    LazyColumn(content = {
        items(list) {
            WellnessTaskItem(taskName = it.label)
        }
    })
}
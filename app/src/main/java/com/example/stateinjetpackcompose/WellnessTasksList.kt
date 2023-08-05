package com.example.stateinjetpackcompose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { getWellnessTasks() }
) {
    LazyColumn(content = {
        items(list) {
            WellnessTaskItem(taskName = it.label)
        }
    })
}

/**
 *  Create dummy data
 */
private fun getWellnessTasks(): List<WellnessTask> {
    return List(30) { i ->
        WellnessTask(i, "Task #$i")
    }
}
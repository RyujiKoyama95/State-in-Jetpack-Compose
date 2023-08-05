package com.example.stateinjetpackcompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        StatefulCounter()
        Spacer(modifier = Modifier.padding(10.dp))

        val list = remember { getWellnessTasks().toMutableStateList() }
        WellnessTasksList(
            list = list,
            onCloseTask = { task -> list.remove(task) }
        )
    }
}

/**
 *  Create dummy data
 */
private fun getWellnessTasks(): List<WellnessTask> {
    return List(30) { i ->
        WellnessTask(i, "Task #$i")
    }
}
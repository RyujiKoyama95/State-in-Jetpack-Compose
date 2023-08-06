package com.example.stateinjetpackcompose

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel: ViewModel() {
    private val _tasks = getWellnessTasks().toMutableStateList()
    val tasks: List<WellnessTask> = _tasks

    fun remove(item: WellnessTask) {
        _tasks.remove(item)
    }
    fun changeTaskChecked(item: WellnessTask, isChecked: Boolean) {
        _tasks.find { it.id == item.id }?.let {
            it.isChecked.value = isChecked
        }
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
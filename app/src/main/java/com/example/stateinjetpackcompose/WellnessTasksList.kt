package com.example.stateinjetpackcompose

private fun getWellnessTasks(): List<WellnessTask> {
    return List(30) { i ->
        WellnessTask(i, "Task #$i")
    }
}
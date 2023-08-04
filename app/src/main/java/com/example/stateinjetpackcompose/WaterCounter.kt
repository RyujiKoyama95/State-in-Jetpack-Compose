package com.example.stateinjetpackcompose

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// 再利用しやすくするため、コンポーザブルにデフォルトのModifierを指定するのがいい
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        var count by remember { mutableStateOf(0) }
        if (count > 0) {
            // showTask=falseの状態で、WellnessTaskItem#onClose()→Button#count=0→Button#count++が走ると、showTask=trueに再初期化される
            // これは、Button#count=0が走ると、再コンポジションが発生するが、val showTaskのコードが呼び出されなかったため、showTaskの状態は破棄される
            var showTask by remember { mutableStateOf(true) }
            Log.d("WaterCounter", "showTask=$showTask")
            if (showTask) {
                WellnessTaskItem(
                    taskName = "Have you taken your 15 minute walk today",
                    onClose = { showTask = false }
                )
            }
            Text(text = "You've had $count glasses.")
        }
        Row() {
            Button(
                onClick = { count++ },
                modifier = Modifier.padding(top = 8.dp),
                enabled = count < 10
            ) {
                Text(text = "Add one")
            }
            Button(
                onClick = { count = 0 },
                modifier = Modifier.padding(top = 8.dp),
                enabled = count > 0
            ) {
                Text(text = "Clear water count")
            }
        }
    }
}
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// 再利用しやすくするため、コンポーザブルにデフォルトのModifierを指定するのがいい
@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // rememberは再コンポーズ後は状態を保持するが、設定の変更後(Activityやプロセスの再作成後)は保持されない
        // 保持されるようにするにはrememberSaveableを使用する
        var count by rememberSaveable{ mutableStateOf(0) }
        if (count > 0) {
            // WellnessTaskItem#onClose()が走り、showTask=falseの状態で、Button#count=0→Button#count++が走ると、showTask=trueに再初期化される
            // これは、Button#count=0が走ると、再コンポジションが発生するが、val showTaskのコードが呼び出されなかったため、showTaskの状態は破棄される
//            var showTask by remember { mutableStateOf(true) }
//            Log.d("WaterCounter", "showTask=$showTask")
//            if (showTask) {
//                WellnessTaskItem(
//                    taskName = "Have you taken your 15 minute walk today",
//                    onClose = { showTask = false }
//                )
//            }
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
//            Button(
//                onClick = { count = 0 },
//                modifier = Modifier.padding(top = 8.dp),
//                enabled = count > 0
//            ) {
//                Text(text = "Clear water count")
//            }
        }
    }
}

/**
 *  StatelessCounterからStatefulCounterにcountをホイスティングする
 *
 *  状態をホイスティングする場合、状態の移動先を決定するために役立つ3つのルールがある
 *  1.少なくとも、状態を使用するすべてのコンポーザブルの最下位レベルの共通の親レベル（読み取り）に状態をホイスティングする
 *  2.少なくとも、変更可能な最上位レベル（書き込み）に状態をホイスティングする
 *  3.同じイベントに応じて2つの状態が変更される場合は、それらを同じレベルにホイスティングする必要がある
 */
@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        if (count > 0) {
            Text(text = "You've had $count glasses.")
        }
        Button(
            onClick = { onIncrement() },
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10
        ) {
            Text(text = "Add one")
        }
        Log.d("StatelessCounter", "count=$count")
    }
}

@Composable
fun StatefulCounter(
    modifier: Modifier = Modifier
) {
    var count by rememberSaveable{ mutableStateOf(0) }
    StatelessCounter(count = count, onIncrement = { count++ }, modifier)
}
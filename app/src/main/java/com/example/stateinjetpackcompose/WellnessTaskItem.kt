package com.example.stateinjetpackcompose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WellnessTaskItem(
    taskName: String,
    isChecked: Boolean,
    onCheckedChanged: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName,
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChanged
        )
        IconButton(onClick = { onClose() }) {
            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close Button")
        }
    }
}

/**
 *  checkedState は、プライベート変数と同様に、各 WellnessTaskItem コンポーザブルに独立して属します。
 *  checkedState を変更すると、LazyColumn 内のすべての WellnessTaskItem インスタンスではなく、
 *  WellnessTaskItem のそのインスタンスのみが再コンポーズされます。
 *
 *  LazyColumn 上のアイテムの場合、アクティビティやプロセスの再作成同様に
 *  rememberでは、スクロールするとアイテムはコンポジションから離れてしまい、表示されなくなります
 */
@Composable
fun WellnessTaskItem(
    taskName: String,
    modifier: Modifier = Modifier
) {
    var checkedState by rememberSaveable { mutableStateOf(false) }
    WellnessTaskItem(
        taskName = taskName,
        isChecked = checkedState,
        onCheckedChanged = { newValue -> checkedState = newValue },
        onClose = {  },
        modifier = modifier)
}
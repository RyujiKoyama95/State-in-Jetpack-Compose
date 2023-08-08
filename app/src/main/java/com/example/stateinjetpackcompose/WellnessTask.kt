package com.example.stateinjetpackcompose

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellnessTask(
    val id: Int,
    val label: String,
    // 以下のように定義すると、isChecked.valueでアクセスすることになる
//    var isChecked: MutableState<Boolean> = mutableStateOf(false)
    initialChecked: Boolean = false
) {
    // コンストラクタに初期値initialCheckedを指定することで、初期値を任意に選択できる。
    // mutableStateOf(false)と直接指定すると、初期値は必ずfalseとなってしまう(このアプリにおいてはどちらでも問題なさそうに見える)
    var isChecked by mutableStateOf(initialChecked)
}
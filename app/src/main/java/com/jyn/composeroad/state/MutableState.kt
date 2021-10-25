package com.jyn.composeroad.state

import androidx.lifecycle.viewmodel.compose.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/*
 * 官网：https://developer.android.google.cn/jetpack/compose/state
 */
@Composable
fun StateTest() {
    /**
     * viewModel()文档
     * https://developer.android.google.cn/jetpack/compose/libraries
     */
    val viewModel: StateViewModel = viewModel() //必须是 = 而不是 by


    /**
     * mutableStateOf
     *      给变量赋予监听数值变化的能力，从而会触发使用该值的View进行重绘。
     * remember
     *      remember 在 mutableStateOf 之上又增加了一层内容：把这个变量的值存储脱离函数，即这个函数再次执行这个值并不会变成初始值。
     * rememberSaveable
     *      在remember 上保证了可以在页面切换的过程中保存数据。
     */

    var remember by remember { mutableStateOf(0) }//不保持状态
    Btn(
        onClick = { remember++ },
        text = "remember Button $remember"
    )

    var rememberSaveable by rememberSaveable { mutableStateOf(0) }//保持状态
    Btn(
        onClick = { rememberSaveable++ },
        text = "rememberSaveable Button $rememberSaveable"
    )

    val liveData by viewModel.liveData.observeAsState()//保持状态
    Btn(
        onClick = { viewModel.liveDataAdd() },
        text = "liveData Button $liveData"
    )

    Btn(
        onClick = { },
        text = "flow Button TODO"
    )

    val rxJava2 by viewModel.observable.subscribeAsState(initial = 0)//不保持状态
    Btn(
        onClick = { viewModel.rxJava2Add(rxJava2) },
        text = "rxJava2 Button $rxJava2"
    )
}

@Composable
fun Btn(onClick: () -> Unit, text: String) {
    Spacer(modifier = Modifier.height(5.dp))
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = text)
    }
}
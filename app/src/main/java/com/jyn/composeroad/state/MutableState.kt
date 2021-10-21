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
 * https://developer.android.google.cn/jetpack/compose/state
 */
@Composable
fun StateTest() {
    /**
     * viewModel()文档
     * https://developer.android.google.cn/jetpack/compose/libraries
     */
    val viewModel: StateViewModel by viewModel()

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
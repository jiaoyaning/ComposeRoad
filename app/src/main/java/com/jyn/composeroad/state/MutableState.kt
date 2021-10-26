package com.jyn.composeroad.state

import android.annotation.SuppressLint
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
import com.jyn.composeroad.base.Btn

/*
 * state官网：https://developer.android.google.cn/jetpack/compose/state
 */


/*
 * =号 创建的mutableStateOf对象，使用的时候需要加value，比如mutableStateOf1.value
 * by 代理创建的mutableStateOf对象则可以直接使用，具体原理是使用了扩展函数
 *
 * PS：by关键字需要用var修饰
 */
private val mutableStateOf1 = mutableStateOf(0) //需要放到全局变量才会有用
private var mutableStateOf2 by mutableStateOf(0)

@SuppressLint("UnrememberedMutableState")
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
     *      remember 在 mutableStateOf 之上又增加了一层内容：把这个变量的值存储脱离函数(可以放在函数体内)，即使这个函数再次执行这个值并不会变成初始值。
     * rememberSaveable
     *      在remember 上保证了可以在页面切换的过程中保存数据。
     */

    Btn(
        onClick = { mutableStateOf1.value += 1 },
        text = "mutableStateOf = ${mutableStateOf1.value}"
    )

    Btn(onClick = { mutableStateOf2 += 1 }, text = "mutableStateOf by $mutableStateOf2")

    var mutableStateOf3 by mutableStateOf(0)

    Btn(onClick = { mutableStateOf3 += 1 }, text = "mutableStateOf局部变量 $mutableStateOf3")

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

package com.jyn.composeroad.state

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apkfuns.logutils.LogUtils
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
    LogUtils.tag("MutableState").i("我被调用了 ----> StateTest()") //每次重绘都会被调用
    /**
     * viewModel()文档
     * https://developer.android.google.cn/jetpack/compose/libraries
     */
    val viewModel: StateViewModel = viewModel() //必须是 = 而不是 by


    /**
     * [mutableStateOf]
     *      给变量赋予监听数值变化的能力，从而会触发使用该值的View进行重绘。
     * [remember]
     *      remember 在 mutableStateOf 之上又增加了一层内容：把这个变量的值存储脱离函数(可以放在函数体内)，即使这个函数再次执行这个值并不会变成初始值。
     * [rememberSaveable]
     *      在remember 上保证了可以在页面切换的过程中保存数据。
     */

    //1. =号 创建的mutableStateOf
    Btn(
        "mutableStateOf = ${mutableStateOf1.value}",
        "1、=号 创建的mutableStateOf"
    ) { mutableStateOf1.value += 1 }


    //2. by关键字创建的mutableStateOf
    Btn(
        "mutableStateOf by $mutableStateOf2",
        "2、by关键字创建的mutableStateOf"
    ) { mutableStateOf2 += 1 }


    //3. 局部变量的mutableStateOf测试
    var mutableStateOf3 by mutableStateOf(0)
    Btn(
        "mutableStateOf局部变量 $mutableStateOf3",
        "3、局部变量的mutableStateOf测试"
    ) { mutableStateOf3 += 1 }


    //4. remember 创建的mutableStateOf PS：其还有一个带key的版本，可根据key值来判断是否更新
    var remember by remember { mutableStateOf(0) }//不保持状态
    Btn(
        "remember Button $remember",
        "4、remember 创建的mutableStateOf"
    ) { remember++ }


    //5. rememberSaveable 创建的mutableStateOf
    var rememberSaveable by rememberSaveable { mutableStateOf(0) }//保持状态
    Btn(
        "rememberSaveable Button $rememberSaveable",
        "5、rememberSaveable 创建的mutableStateOf"
    ) { rememberSaveable++ }


    //6. liveData转State
    val liveData by viewModel.liveData.observeAsState()//保持状态
    Btn(
        "liveData Button $liveData",
        "6、liveData转State"
    ) { viewModel.liveDataAdd() }


    //7. flow转State
    Btn("flow Button TODO", "7、flow转State") { }


    //8. rxjava转State
    val rxJava2 by viewModel.observable.subscribeAsState(initial = 0)//不保持状态
    Btn(
        "rxJava2 Button $rxJava2",
        "8、rxjava转State"
    ) { viewModel.rxJava2Add(rxJava2) }
}

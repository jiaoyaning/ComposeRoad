package com.jyn.composeroad.effect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState

/*
 * https://juejin.cn/post/7016583915292852254
 * https://juejin.cn/post/6930785944580653070
 */
class EffectActivity : AppCompatActivity() {
    /*
     * composable函数不应该包含Side-effect(副作用)，但是当composable函数需要修改app的状态时，
     * 这个composable函数需要从一个受控制的环境中被调用，此环境需要感知到本composable函数的生命周期，此时就需要使用Side-effect。
     *
     *
     * 副作用必须在合适的时机执行，首先需要明确一个Composable的生命周期：
     *      onActive（or onEnter）：当Composable首次进入组件树时
     *      onCommit（or onUpdate）：UI随着recomposition发生更新时
     *      onDispose（or onLeave）：当Composable从组件树移除时
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /**
     * [DisposableEffect]
     *  DisposableEffect相当于早期API中的 onCommit + onDispose，顾名思义，DisposableEffect可以感知onCommit和onDipose的生命周期回调，在里面进行Effect处理。
     */
    @Composable
    fun DisposableEffectTest() {
    }

    /**
     * [SideEffect]
     *  SideEffect相当于DisposableEffect的简化版，当不需要onDispose、不需要参数控制（即每次onCommit都执行）时使用
     */

    /**
     * [LaunchedEffect] (在composable函数中创建coroutine，执行耗时函数)
     *
     *   1、LaunchedEffect会启动一个协程，未执行完毕则取消。
     *   2、发生重组时，如果其key不变，则LaunchedEffect不会被重新启动。
     *
     * LaunchedEffect常用于将一些只需要在Composition中执行一次的操作抽离出来，防止其在recompose时被反复执行，例如注册事件的监听等。
     */

    /**
     * [rememberCoroutineScope] (在非composable函数中启动coroutine，便于开发者自己控制coroutine的生命周期。)
     *
     *   1、rememberCoroutineScope可以返回一个coroutineScope，便于开发者手动控制该coroutine的生命周期，例如有用户点击事件时启动该coroutine。
     *   2、rememberCoroutineScope返回的coroutineScope会和其调用点的生命周期保持一致，当调用点所在的Composition退出时，该coroutineScope会被取消。
     */

    /**
     * [rememberUpdatedState] (LaunchedEffect增强，保持参数的值在使用时是最新值)
     */
}
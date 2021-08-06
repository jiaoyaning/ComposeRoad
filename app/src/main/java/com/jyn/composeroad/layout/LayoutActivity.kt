package com.jyn.composeroad.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/*
 * 布局概述
 * https://compose.net.cn/layout/overview/
 *
 * Jetpack Compose 基础 | 布局
 * https://juejin.cn/post/6952129655264673805
 */
class LayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                Box()
                Column()
                Row()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Box() {

    }

    @Preview(showBackground = true)
    @Composable
    fun Column() {

    }

    @Preview(showBackground = true)
    @Composable
    fun Row() {

    }

    @Preview(showBackground = true)
    @Composable
    fun ConstraintLayout() {

    }

}
package com.jyn.composeroad.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.jyn.composeroad.ui.theme.ComposeRoadTheme

/*
 * 自定义布局概述
 * https://docs.compose.net.cn/layout/custom_layout/
 *
 * Jetpack Compose 基础 | 布局
 * https://juejin.cn/post/6952129655264673805
 */
class LayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeRoadTheme { LayoutList() } }
    }

    /**
     * 当使用 layout 修饰符时，你传入的回调 lambda 需要包含两个参数：measurable、constraints
     *  measurable      子元素的测量句柄，通过提供的api完成测量与布局过程
     *  constraints     子元素的测量约束，包括宽度与高度的最大值与最小值
     */

    @Composable
    fun LayoutList() {
        LazyColumn(
            contentPadding = PaddingValues(10.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

        }
    }
}
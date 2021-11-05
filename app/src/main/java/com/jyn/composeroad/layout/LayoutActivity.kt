package com.jyn.composeroad.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import com.jyn.composeroad.ui.theme.ComposeRoadTheme
import kotlin.math.roundToInt

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
            item { LayoutSimple() }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LayoutSimple() {
        Box {
            Text(
                text = "自定义layout，添加一个Padding",
                modifier = Modifier
                    .background(Color.Red)
                    .layout { measurable, constraints ->
                        val padding = 8.dp
                            .toPx()
                            .roundToInt()

                        val paddedConstraints = constraints
                            .copy()
                            .apply {
                                constrainWidth(maxWidth - padding * 2)
                                constrainHeight(maxHeight - padding * 2)
                            }

                        /**
                         * 完成子元素的测绘
                         *  如果将 lambda 的 constraints 直接传入则意味着：你将父元素给当前元素的限制直接提供了当前元素的子元素，自身没有增加任何额外的限制。
                         *  子元素测量的结果被包装在一个 Placeable 实例中，可通过该Placeable 实例获取子元素测量结果。
                         */
                        val placeable = measurable.measure(paddedConstraints)

                        layout(placeable.width + padding * 2, placeable.height + padding * 2) {
                            //placeRelative()方法用来完成子元素的布局流程
                            placeable.placeRelative(padding, padding)
                        }
                    }
                    .background(Color.Green)
            )
        }
    }
}
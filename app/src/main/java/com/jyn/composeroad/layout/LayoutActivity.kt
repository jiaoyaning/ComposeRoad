package com.jyn.composeroad.layout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyn.composeroad.ui.theme.ComposeRoadTheme
import kotlin.math.max
import kotlin.math.roundToInt

/*
 * 自定义布局概述
 * https://docs.compose.net.cn/layout/custom_layout/
 * 固有特性测量
 * https://docs.compose.net.cn/layout/intrinsic/
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
            item { LayoutView() }
            item { IntrinsicView() }
        }
    }

    /**
     * 一、Modifier.layout
     */
    @Preview(showBackground = true)
    @Composable
    fun LayoutSimple() {
        Box {
            Text(
                text = "自定义 Modifier.layout，添加一个Padding",
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

    /**
     * Layout()
     */
    @Preview(showBackground = true)
    @Composable
    fun LayoutView() {
        /**
         * content          在 content 中声明所有子元素信息
         * measurePolicy    默认场景下只实现 measure 即可，上面示例中最后传入的 lambda 就是 measure 的实现。
         *      当你想要为你的 Layout Composable 适配 Intrinsics 时(官方中文翻译为固有特性测量)，
         *      则需要重写 minIntrinsicWidth 、minIntrinsicHeight、maxIntrinsicWidth 、maxIntrinsicHeight 方法。
         */
        Layout(
            content = {
                Box(
                    Modifier
                        .size(100.dp)
                        .background(Color.Red),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "布局一", color = Color.White)
                }
                Box(
                    Modifier
                        .size(100.dp)
                        .background(Color.Blue),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "布局二", color = Color.White)
                }
                Box(
                    Modifier
                        .size(100.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "布局三", color = Color.White)
                }
            },
            measurePolicy = { measurables, constraints ->
                /**
                 * 一、测量子元素并将结果保存到 placeables 中
                 */
                var width = 0
                var height = 0
                val placeables: List<Placeable> = measurables.map { measurable ->
                    val placeable = measurable.measure(constraints)
                    height = max(height, placeable.height)
                    width += placeable.width
                    placeable
                }

                /**
                 * 二、根据测绘结果布局
                 *      width:布局的宽度(整体)
                 *      height:布局的高度(整体)
                 */
                var xPosition = 0
                layout(width, height) {
                    placeables.forEach { placeable ->
                        placeable.placeRelative(xPosition, 0)
                        xPosition += placeable.width
                    }
                }
            })
    }

    /**
     * Intrinsic 固有特性测量
     *  1.不限制的情况下，会是多大尺寸
     *  2.限制宽时，会是多高；限制高时，会是多宽
     */
    @Preview(showBackground = true)
    @Composable
    fun IntrinsicView() {
        /**
         * IntrinsicSize.Min : 用最小尺寸来当做自己的尺寸
         */
        Row(
            Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
        ) {
            Text(
                text = "我是布局一",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
            )
            Divider(
                Modifier
                    .width(1.dp)
                    .fillMaxHeight(), color = Color.Red
            )
            Text(
                text = "我是布局二",
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
            )
        }
    }


}
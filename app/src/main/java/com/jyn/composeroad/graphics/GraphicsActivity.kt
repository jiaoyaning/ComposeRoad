package com.jyn.composeroad.graphics

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jyn.composeroad.base.BaseActivity
import com.jyn.composeroad.ui.theme.ComposeRoadTheme

/*
 * 绘制 : 构建和处理自定义图形的功能
 * https://developer.android.google.cn/jetpack/compose/graphics
 * https://docs.compose.net.cn/design/draw/custom_draw/
 *
 * Jetpack-Compose 学习笔记（三）—— Compose 的自定义“View”
 * https://blog.csdn.net/lbs458499563/article/details/120212671
 *
 * Jetpack Compose实现的天气动画！可爱~
 * https://mp.weixin.qq.com/s/ZVxwOLUFR7xIdoUazhPynA
 *
 * JetPack-Compose 水墨画效果👍👍👍
 * https://juejin.cn/post/6947700226858123271
 */
class GraphicsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeRoadTheme { GraphicsList() } }
    }

    //    @Preview(showBackground = true)
    @Composable
    fun GraphicsList() {
        LazyColumn(
            contentPadding = PaddingValues(10.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { CanvasSimple() }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CanvasSimple() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) { Text(text = "Canvas 实例") }

            val radius = 40.dp
            Canvas(modifier = Modifier.size(radius)) {
                drawCircle( // 画圆
                    brush = Brush.sweepGradient(
                        listOf(Color.Red, Color.Green, Color.Red),
                        Offset(radius.toPx() / 2f, radius.toPx() / 2f)
                    ),
                    radius = (radius / 2f).toPx(),
                    style = Stroke(
                        width = 5.dp.toPx()
                    )
                )
            }
        }
    }



}
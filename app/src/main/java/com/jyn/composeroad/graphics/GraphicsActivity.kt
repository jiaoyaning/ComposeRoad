package com.jyn.composeroad.graphics

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyn.composeroad.R
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

    /**
     * 对于自定义绘制，官方为我们提供了三个 Modifier API
     *      drawWithContent()
     *      drawBehind()
     *      drawWithCache()
     */

    //    @Preview(showBackground = true)
    @Composable
    fun GraphicsList() {
        LazyColumn(
            contentPadding = PaddingValues(10.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { CanvasSimple() }

            item { DrawWithContentView() }

            item { DrawBehindView() }

            item { DrawWithCache() }
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
                    style = Stroke(width = 5.dp.toPx())
                )
            }
        }
    }

    /**
     * 一、drawWithContent
     */
    @Preview(showBackground = true)
    @Composable
    fun DrawWithContentView() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = "drawWithContent{} 类似View系统的onDraw",
                    fontSize = 13.sp,
                    modifier = Modifier.drawWithContent {
                        drawRect(Color.Gray)
                        drawContent()
                    })
            }
            Card(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .size(40.dp)
                    .drawWithContent {
                        drawContent()
                        drawCircle(
                            color = Color.Red,
                            radius = 5.dp.toPx(),
                            center = Offset(drawContext.size.width, 0f)
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.icon_master_road2),
                    contentDescription = "Diana"
                )
            }
        }
    }

    /**
     * 二、drawBehind
     */
    @Preview(showBackground = true)
    @Composable
    fun DrawBehindView() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "drawBehind{} 最先绘制，相当于背景",
                    fontSize = 13.sp
                )
            }
            Card(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .size(40.dp)
                    .drawBehind {
                        drawCircle(
                            color = Color.Red,
                            radius = 5.dp.toPx(),
                            center = Offset(drawContext.size.width, 0f)
                        )
                    }
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.icon_master_road3),
                    contentDescription = "Diana"
                )
            }
        }
    }

    /**
     * 三、drawWithCache
     * 可以设置缓存，这样在Composable重组的时候可以避免重复初始化
     */
    @Preview(showBackground = true)
    @Composable
    fun DrawWithCache() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "drawWithCache{} 设置缓存，避免多次初始化",
                    fontSize = 13.sp
                )
            }

            val size = 40.dp
            var flag by remember { mutableStateOf(true) }
            Card(
                modifier = Modifier
                    .size(size)
                    .drawWithCache {
                        //这个path只会初始化一次
                        val path = Path().apply {
                            moveTo(0f, 0f)
                            relativeLineTo(size.toPx(), 0f)
                            relativeLineTo(0f, size.toPx())
                            relativeLineTo(-size.toPx(), 0f)
                            relativeLineTo(0f, -size.toPx())
                        }

                        //需要返回 onDrawWithContent 或者 onDrawBehind
                        onDrawWithContent {
                            drawContent()
                            drawPath(
                                path = path,
                                color = if (flag) Color.Red else Color.Blue,
                                style = Stroke(width = 10f)
                            )
                        }
                    }
                    .clickable { flag = !flag }
            ) {
                Image(
                    painter = painterResource(id = R.mipmap.icon_master_road),
                    contentDescription = "Diana"
                )
            }
        }
    }
}
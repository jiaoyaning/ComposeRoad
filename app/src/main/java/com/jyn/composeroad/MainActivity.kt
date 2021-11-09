package com.jyn.composeroad

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jyn.composeroad.base.BaseActivity
import com.jyn.composeroad.layout.LayoutActivity
import com.jyn.composeroad.state.StateActivity
import com.jyn.composeroad.state.StateViewModel
import com.jyn.composeroad.ui.theme.ComposeRoadTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.lifecycle.viewmodel.compose.*
import com.jyn.composeroad.animation.AnimationActivity
import com.jyn.composeroad.base.Btn
import com.jyn.composeroad.graphics.GraphicsActivity
import com.jyn.composeroad.gesture.GestureActivity

/*
 * 官方教程 & 文档
 * https://developer.android.google.cn/jetpack/compose
 * https://developer.android.google.cn/jetpack/compose/documentation
 *
 * Compose 修饰符列表 Modifier列表
 * https://developer.android.com/jetpack/compose/modifiers-list?hl=zh-cn
 *
 * Compose博物馆：https://compose.net.cn/
 *
 * 深入详解 Jetpack Compose | 优化 UI 构建 | 实现原理
 * https://juejin.cn/post/6885900954307133448
 * https://juejin.cn/post/6889797083667267598
 *
 * 图解 Modifier 背后原理 ，竟如此简单！
 * https://juejin.cn/post/6986933061845778446
 *
 * Jetpack Compose 测量流程源码分析
 * https://juejin.cn/post/6981805443219718151
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    /**
     * 此处跟 Composable 中 [viewModel] 方法所生成的对象并不是同一个
     */
    private val stateViewModel: StateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         *[setContent]方法不需要设置[Composable]注解是因为它只起到传递作用，真正需要添加的应该这个lambda作用域
         */
        setContent { ComposeRoadTheme { ListTest() } }
    }

    /*
     * 一、@Preview 常用的参数如下：
     *      name: String:               为该Preview命名，该名字会在布局预览中显示。
     *      showBackground: Boolean:    是否显示背景，true为显示。
     *      backgroundColor: Long:      设置背景的颜色。
     *      showDecoration: Boolean:    是否显示Statusbar和Toolbar，true为显示。
     *      group: String:              为该Preview设置group名字，可以在UI中以group为单位显示。
     *      fontScale: Float:           可以在预览中对字体放大，范围是从0.01。
     *      widthDp: Int:               在Compose中渲染的最大宽度，单位为dp。
     *      heightDp: Int:              在Compose中渲染的最大高度，单位为dp。
     *
     * 二、@Composable 作用
     *      用来给编译器(非注解处理器)来识别并插入额外的参数和调用，如`$composer.start(123)`和`$composer.end()`，
     *      这样在UI更新的时候，可以在 [间隙缓冲区] 中快速找出被更新的位置，并发动重组。
     *
     *      @Composable 类似 suspend 都是编译器在额外处理，
     *      至于为什么不能把@Composable也设计成关键字？因为@Composable是属于jetpack的UI框架，而不是kotlin的语言特性
     */
    @Preview(showBackground = true)
    @Composable
    fun ListTest() {
        LazyColumn(
            contentPadding = PaddingValues(
                horizontal = 10.dp,//水平边缘（左和右）添加 10.dp 的 padding
                vertical = 10.dp //然后在内容的顶部和底部添加 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(5.dp) //在每个项目之间增加了 4.dp 的空间
        ) {
            // Image
            item { Image() }

            // 颜色(Color) 排版(Typography) 形状(Shape)
            item { Typography() }

            /*
             * Spacer
             * https://compose.net.cn/layout/spacer/
             */
            item { Spacer(modifier = Modifier.height(5.dp)) }

            // 状态管理
            item { Btn("状态管理") { goto(StateActivity::class.java) } }

            /*
             * 布局 :
             * https://developer.android.google.cn/jetpack/compose/layouts
             */
            item { Btn("布局") { goto(LayoutActivity::class.java) } }

            /*
             * 绘制 : 构建和处理自定义图形的功能
             * https://developer.android.google.cn/jetpack/compose/graphics
             * https://docs.compose.net.cn/design/draw/custom_draw/
             */
            item { Btn("自定义绘制") { goto(GraphicsActivity::class.java) } }

            /*
             * 动画
             * https://compose.net.cn/design/animation/overview/
             * https://developer.android.google.cn/jetpack/compose/animation
             */
            item { Btn("动画") { goto(AnimationActivity::class.java) } }

            /*
             * 手势 : 检测用户手势并与之互动
             * https://compose.net.cn/design/gesture/overview/
             * https://developer.android.google.cn/jetpack/compose/gestures
             */
            item { Btn("手势") { goto(GestureActivity::class.java) } }

            /*
             * 主题 : 如何为基于 Compose 的界面设置主题背景
             * https://compose.net.cn/design/theme/overview/
             * https://developer.android.google.cn/jetpack/compose/themes
             */
            item { }

            /*
             * 列表 : 管理和显示数据列表的一些选项
             * https://compose.net.cn/design/lists/overview/
             * https://developer.android.google.cn/jetpack/compose/lists
             */
            item { }

            /*
             * 文本
             * https://developer.android.google.cn/jetpack/compose/text
             */
            item { }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Image() {
        Surface(
            shape = MaterialTheme.shapes.medium, // 使用 MaterialTheme 自带的形状
            elevation = 5.dp,
        ) {
            Image(
                painter = painterResource(id = R.mipmap.image_compose_tutorial),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Typography() {
        // 创建一个能够检测卡片十分被展开的变量
        var isExpanded by remember { mutableStateOf(false) }

        // 创建一个能够根据 isExpanded 变量值而改变颜色的变量
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) Color(0xFFCCCCCC) else MaterialTheme.colors.surface
        )

        //竖向排列
        Column(modifier = Modifier
            // 添加 Modifier 的 clickable 扩展方法，可以让元素具有点击的效果，顺序很重要
            .clickable { isExpanded = !isExpanded }
            .background(color = surfaceColor)
            //padding放background前面会产生margin效果
            .padding(all = 5.dp)
        ) {
            //横向排列
            Row {
                Image(
                    painter = painterResource(id = R.mipmap.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(20.dp)
                        .clip(CircleShape)
                        // 添加边框
                        .border(
                            1.5.dp,
                            MaterialTheme.colors.secondary,
                            shape = CircleShape
                        )
                        //对齐方式
                        .align(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Text(
                    "Hello Compose",
                    color = MaterialTheme.colors.secondaryVariant,
                    style = TextStyle(fontSize = 20.sp)
                )
            }
            Text(
                text = "Jetpack Compose 是用于构建原生界面的最新的 Android 工具包，采用声明式 UI 的设计，拥有更简单的自定义和实时的交互预览功能，由 Android 官方团队全新打造的 UI 框架",
                style = MaterialTheme.typography.body2,
                maxLines = if (isExpanded) Int.MAX_VALUE else 2, // 修改 maxLines 参数，在默认情况下，只显示2行文本内容
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.animateContentSize() // Composable 大小的动画效果
            )
        }
    }
}
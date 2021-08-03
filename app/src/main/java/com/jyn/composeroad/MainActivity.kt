package com.jyn.composeroad

import android.os.Bundle
import androidx.activity.compose.setContent
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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import com.jyn.composeroad.state.StateActivity
import com.jyn.composeroad.ui.theme.ComposeRoadTheme
import dagger.hilt.android.AndroidEntryPoint

/*
 * 官方文档
 * https://developer.android.google.cn/jetpack/compose
 *
 * Compose博物馆：https://compose.net.cn/
 * https://github.com/compose-museum/hi-compose
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeRoadTheme { ListTest() } }
    }

    /*
     * Preview的注解中比较常用的参数如下：
     *  name: String: 为该Preview命名，该名字会在布局预览中显示。
     *  showBackground: Boolean: 是否显示背景，true为显示。
     *  backgroundColor: Long: 设置背景的颜色。
     *  showDecoration: Boolean: 是否显示Statusbar和Toolbar，true为显示。
     *  group: String: 为该Preview设置group名字，可以在UI中以group为单位显示。
     *  fontScale: Float: 可以在预览中对字体放大，范围是从0.01。
     *  widthDp: Int: 在Compose中渲染的最大宽度，单位为dp。
     *  heightDp: Int: 在Compose中渲染的最大高度，单位为dp。
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

            // Text 颜色(Color) 排版(Typography) 形状(Shape)
            item { Typography() }

            // Spacer
            item { Spacer(modifier = Modifier.height(5.dp)) }

            // Button & 状态管理
            item { ButtonAndState() }
        }
    }

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

    @Composable
    fun Typography() {
        // 创建一个能够检测卡片十分被展开的变量
        var isExpanded by remember { mutableStateOf(false) }

        // 创建一个能够根据 isExpanded 变量值而改变颜色的变量
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) Color(0xFFCCCCCC) else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier
            .padding(all = 5.dp)
            .background(surfaceColor)
            // 添加 Modifier 的 clickable 扩展方法，可以让元素具有点击的效果
            .clickable { isExpanded = !isExpanded }
        ) {
            Row {
                Image(
                    painter = painterResource(id = R.mipmap.logo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(26.dp)
                        .clip(CircleShape)
                        // 添加边框
                        .border(
                            1.5.dp,
                            MaterialTheme.colors.secondary,
                            shape = CircleShape
                        )
                )
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

    @Composable
    fun ButtonAndState() {
        Button(
            onClick = { goto(StateActivity::class.java) },
            modifier = Modifier.fillMaxWidth()
        ) { Text(text = "Button & 状态管理") }
    }
}
package com.jyn.composeroad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.jyn.composeroad.state.Btn
import com.jyn.composeroad.state.StateTest

/*
 * 官方文档
 * https://developer.android.google.cn/jetpack/compose
 *
 * Compose博物馆
 * https://docs.compose.net.cn
 */
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListTest()
        }
    }

    /**
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
    @Preview
    @Composable
    fun ListTest() {
        LazyColumn(
            modifier = Modifier.background(Color.White),
            contentPadding = PaddingValues(
                horizontal = 10.dp,//水平边缘（左和右）添加 10.dp 的 padding
                vertical = 10.dp //然后在内容的顶部和底部添加 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(5.dp) //在每个项目之间增加了 4.dp 的空间
        ) {
            // ImageView
            item {
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
            // Text
            item {
                Text(
                    text = "Hello Compose",
                    style = TextStyle(fontSize = 20.sp)
                )
                Text(
                    text = "Jetpack Compose 是用于构建原生界面的最新的 Android 工具包，采用声明式 UI 的设计，拥有更简单的自定义和实时的交互预览功能，由 Android 官方团队全新打造的 UI 框架",
                    style = MaterialTheme.typography.body2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            /*
             * Button & 状态管理
             */
            item { StateTest(viewModel) }
        }
    }
}
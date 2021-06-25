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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
//            PreviewTest()
            ListTest()
        }
    }

    @Preview
    @Composable
    fun PreviewTest() {
        Column(modifier = Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = R.mipmap.icon_master_road),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Hello Compose",
                style = TextStyle(fontSize = 20.sp)
            )
            Text(
                text = "这是一个Preview的文字，可以很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长",
                style = MaterialTheme.typography.body2,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }

    @Composable
    fun ListTest() {
        LazyColumn(
            modifier = Modifier
                .background(Color.White),
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
            // Button
            item {
                Button(onClick = { viewModel.btnAdd() }) {
                    Text(
                        text = "这是一个Button ${viewModel.btnInt.value}",
                    )
                }
            }
        }
    }
}
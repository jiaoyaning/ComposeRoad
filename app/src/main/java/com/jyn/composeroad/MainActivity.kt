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
import com.jyn.composeroad.状态.Btn

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
             * https://developer.android.google.cn/jetpack/compose/state
             */
            item {
                var remember by remember { mutableStateOf(0) }//不保持状态
                Btn(
                    onClick = { remember++ },
                    text = "remember Button $remember"
                )

                Spacer(modifier = Modifier.height(5.dp))

                var rememberSaveable by rememberSaveable { mutableStateOf(0) }//保持状态
                Btn(
                    onClick = { rememberSaveable++ },
                    text = "rememberSaveable Button $rememberSaveable"
                )

                Spacer(modifier = Modifier.height(5.dp))

                val liveData by viewModel.liveData.observeAsState()//保持状态
                Btn(
                    onClick = { viewModel.liveDataAdd() },
                    text = "liveData Button $liveData"
                )

                Spacer(modifier = Modifier.height(5.dp))

                val rxJava2 by viewModel.observable.subscribeAsState(initial = 0)//不保持状态

                Btn(
                    onClick = { viewModel.rxJava2Add(rxJava2) },
                    text = "rxJava2 Button $rxJava2"
                )
            }
        }
    }
}
package com.jyn.composeroad.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class StateActivity : ComponentActivity() {
    /**
     * 此ViewModel跟 Composable 中 [viewModel] 方法所生成的对象并不是同一个
     */
    private val stateViewModel: StateViewModel by viewModels()

    /*
     * =号 创建的mutableStateOf对象，使用的时候需要加value，比如mutableStateOf1.value
     * by 代理创建的mutableStateOf对象则可以直接使用，具体原理是使用了扩展函数
     */
    val mutableStateOf1 = mutableStateOf(0)
    val mutableStateOf2 by mutableStateOf(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { StateView() }
    }

    @Preview
    @Composable
    fun StateView() {
        LazyColumn(
            modifier = Modifier.background(Color.White),
            contentPadding = PaddingValues(
                horizontal = 10.dp,//水平边缘（左和右）添加 10.dp 的 padding
                vertical = 10.dp //然后在内容的顶部和底部添加 10.dp
            ),
            verticalArrangement = Arrangement.spacedBy(5.dp) //在每个项目之间增加了 4.dp 的空间
        ) {
            /*
           * Button & 状态管理
           */
            item { StateTest() }
        }
    }
}
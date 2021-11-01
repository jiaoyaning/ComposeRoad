package com.jyn.composeroad.animation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jyn.composeroad.base.BaseActivity
import com.jyn.composeroad.ui.theme.ComposeRoadTheme

/*
 * 动画
 * https://compose.net.cn/design/animation/overview/
 * https://developer.android.google.cn/jetpack/compose/animation
 */
class AnimationActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeRoadTheme { AnimationList() } }
    }

    @Composable
    @Preview(showBackground = true)
    fun AnimationList() {
        LazyColumn(
            contentPadding = PaddingValues(10.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp) //在每个项目之间增加了 4.dp 的空间
        ) {
            //显示隐藏动画
            item { AnimatedVisibilityView() }

            //自定义效果的显示隐藏动画
            item { AnimatedVisibilityView2() }
        }
    }

    /**
     * 显示隐藏动画
     */
    @OptIn(ExperimentalAnimationApi::class)
    @Preview(showBackground = true)
    @Composable
    fun AnimatedVisibilityView() {
        var state by remember { mutableStateOf(true) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            AnimatedVisibility(
                visible = state,
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
            ) {
                Text(text = "这是一个显示隐藏动画文本")
            }
            Spacer(Modifier.width(5.dp))
            Button(onClick = { state = !state }) {
                Text(if (state) "隐藏" else "显示")
            }
        }
    }

    /**
     * 显示隐藏动画,自定义效果
     */
    @OptIn(ExperimentalAnimationApi::class)
    @Preview(showBackground = true)
    @Composable
    fun AnimatedVisibilityView2() {
        var state by remember { mutableStateOf(true) }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            AnimatedVisibility(
                visible = state,
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically),
                enter = fadeIn(), //可以定制复杂效果
                exit = fadeOut()
            ) {
                Text(text = "这是一个自定义效果的显示隐藏动画文本")
            }
            Spacer(Modifier.width(5.dp))
            Button(onClick = { state = !state }) {
                Text(if (state) "隐藏" else "显示")
            }
        }
    }
}
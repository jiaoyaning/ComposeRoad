package com.jyn.composeroad.animation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

    @Preview(showBackground = true)
    @Composable
    fun AnimationList() {
        LazyColumn(
            contentPadding = PaddingValues(10.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            //显示隐藏动画
            item { AnimatedVisibilityView() }

            //自定义效果的显示隐藏动画
            item { AnimatedVisibilityView2() }

            //尺寸更改时动画
            item { AnimateContentSizeView() }

            //淡入淡出动画
            item { CrossfadeView() }

            //单个值改变动画
            item { AnimateFloatAsState() }
        }
    }


    /**
     * 显示隐藏动画
     */
    @OptIn(ExperimentalAnimationApi::class)
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
                Text(text = "AnimatedVisibility 显示隐藏动画")
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
    @Composable
    fun AnimatedVisibilityView2() {
        var visiable by remember { mutableStateOf(true) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.End
        ) {
            AnimatedVisibility(
                visible = visiable,
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically),
                enter = fadeIn(), //可以定制复杂效果
                exit = fadeOut()
            ) {
                Text(text = "AnimatedVisibility 自定义效果的显示隐藏动画")
            }
            Spacer(Modifier.width(5.dp))
            Button(
                onClick = { visiable = !visiable },
                modifier = Modifier.align(CenterVertically)
            ) {
                Text(if (visiable) "隐藏" else "显示")
            }
        }
    }


    /**
     * 对尺寸更改进行动画
     */
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun AnimateContentSizeView() {
        var isExpanded by remember { mutableStateOf(false) }
        Box(Modifier.fillMaxWidth()) {
            Text(
                text = ".animateContentSize() 尺寸改变时动画，点击可缩放行数 \n 这是占位第二行",
                overflow = TextOverflow.Ellipsis,
                maxLines = if (isExpanded) 3 else 1,
                modifier = Modifier
                    .animateContentSize()
                    .clickable { isExpanded = !isExpanded }
            )
        }
    }


    /**
     * 在两个布局之间用交叉淡入淡出的动画
     */
    @SuppressLint("UnusedCrossfadeTargetStateParameter")
    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    fun CrossfadeView() {
        var isCut by remember { mutableStateOf(true) }
        Row(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
        ) {
            Crossfade(
                targetState = isCut,
                animationSpec = tween(1000),
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
            ) {
                when (it) {
                    //可以抽取成独立的 Composable
                    true -> Box(
                        contentAlignment = Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(Color.Red),
                    ) {
                        Text(
                            text = "Crossfade 淡入淡出 布局A",
                            color = Color.White,
                        )
                    }
                    false -> Box(
                        contentAlignment = Center,
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .background(Color.Blue),
                    ) {
                        Text(
                            text = "淡入淡出 Crossfade 样式B",
                            color = Color.White,
                        )
                    }
                }
            }

            Spacer(Modifier.width(5.dp))

            Button(
                onClick = { isCut = !isCut },
                modifier = Modifier.align(CenterVertically)
            ) {
                Text("切换")
            }
        }
    }


    /**
     * animate*AsState 单个值动画，只需提供结束值（或目标值），API 就会从当前值到指定值开始动画。
     */
    @Composable
    fun AnimateFloatAsState() {
        var enabled by remember { mutableStateOf(true) }
        val alpha: Float by animateFloatAsState(
            if (enabled) 1f else 0f,
            animationSpec = tween(1000),
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                contentAlignment = Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .graphicsLayer(alpha = alpha)
                    .background(Color.Blue),
            ) {
                Text(
                    text = "animate*AsState 单个值修改动画",
                    color = Color.White,
                )
            }

            Spacer(Modifier.width(5.dp))

            Button(
                onClick = { enabled = !enabled },
                modifier = Modifier.align(CenterVertically)
            ) {
                Text(if (enabled) "淡出" else "淡入")
            }
        }
    }
}
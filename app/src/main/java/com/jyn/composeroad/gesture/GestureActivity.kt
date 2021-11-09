package com.jyn.composeroad.gesture

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.jyn.composeroad.base.BaseActivity
import com.jyn.composeroad.ui.theme.ComposeRoadTheme
import kotlin.math.roundToInt

/*
 * 手势 : 检测用户手势并与之互动
 * https://compose.net.cn/design/gesture/overview/
 * https://developer.android.google.cn/jetpack/compose/gestures
 */
class GestureActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeRoadTheme { GestureView() } }
    }

    @Composable
    fun GestureView() {
        LazyColumn(
            contentPadding = PaddingValues(10.dp, 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item { ClickableView() }

            item { PointerInputView() }

            item { ScrollView() }

            item { ScrollableView() }

            item { DraggableView() }

            item { SwipeableView() }

            item { TransformerView() }
        }
    }

    /**
     * clickable 修饰符允许应用检测对已应用该修饰符的元素的点击。
     */
    @Preview(showBackground = true)
    @Composable
    fun ClickableView() {
        var count by remember { mutableStateOf(0) }
        Box(
            Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.Gray)
                .clickable { count++ },
            contentAlignment = Alignment.Center
        ) { Text(text = "$count") }
    }

    /**
     * 点按手势检测器
     */
    @Composable
    fun PointerInputView() {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = { toast("双击") },
                        onLongPress = { toast("长按") },
                        onPress = { toast("按下") },
                        onTap = { toast("单击") }
                    )

                    forEachGesture {
                        awaitPointerEventScope {
                            val event = awaitPointerEvent()
                        }
                    }
                }
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.Gray)
        ) { Text(text = "pointerInput 测试") }
    }

    /**
     * 滚动修饰符
     * verticalScroll 和 horizontalScroll 修饰符提供一种最简单的方法，可让用户在元素内容边界大于最大尺寸约束时滚动元素。
     */
    @Composable
    fun ScrollView() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(Color.Gray)
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(10) {
                Text("Item $it", modifier = Modifier.padding(2.dp))
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }

    /**
     * 可滚动的修饰符
     * scrollable 修饰符与滚动修饰符不同，区别在于 scrollable 可检测滚动手势，但不会偏移其内容。
     */
    @Composable
    fun ScrollableView() {
        var offset by remember { mutableStateOf(0f) }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Gray)
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollableState { delta ->
                        offset += delta
                        delta
                    })
        ) {
            Text("$offset")
        }
    }

    /**
     * 拖动手势偏移量
     * draggable 修饰符只能监听垂直方向偏移或水平方向偏移。
     *
     *  draggableState：通过 draggableState 可以获取到拖动手势的偏移量，并允许开发者动态控制偏移量
     *  orientation：监听的拖动手势方向，只能是水平方向(Orientation.Horizontal)或垂直方向(Orientation.Vertical)
     */
    @Composable
    fun DraggableView() {
        var offsetX by remember { mutableStateOf(0f) }
        var maxWidth by remember { mutableStateOf(0) }
        val size = with(LocalDensity.current) { 40.dp.toPx() }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { maxWidth = it.width }
                .background(Color.Gray)
        ) {
            Box(modifier = Modifier
                .size(40.dp)
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .draggable(orientation = Orientation.Horizontal,
                    //delta 滑动距离
                    state = rememberDraggableState { delta ->
                        offsetX = (offsetX + delta).coerceIn(0f, maxWidth.toFloat() - size)
                    }
                )
                .background(Color.Red))
        }
    }

    /**
     * 吸附效果的滑动
     * 与 draggable 修饰符不同的是， swipeable 修饰符允许开发者通过锚点设置从而实现组件呈现吸附效果的动画，常用于开关等动画，也可用于下拉刷新等特殊效果的实现。
     *
     *  swipeableState：通过 swipeableState 的设置可以获取到当前手势的偏移量信息
     *  anchors：锚点，可以通过锚点设置在不同状态时所应该对应的偏移量信息
     *  orientation：手势方向，被修饰组件的手势方向只能是水平或垂直
     *  thresholds (常用非必需)：常用作定制不同锚点间吸附效果的临界阈值，常用有 FixedThreshold(Dp) 和FractionalThreshold(Float)等
     */
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun SwipeableView() {
        var maxWidth by remember { mutableStateOf(0) }
        val size = with(LocalDensity.current) { 40.dp.toPx() }
        val state = rememberSwipeableState(true)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { maxWidth = it.width }
                .background(Color.Gray)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .offset { IntOffset(state.offset.value.toInt(), 0) }
                    .swipeable(
                        orientation = Orientation.Horizontal,
                        state = state,
                        anchors = mapOf(
                            0f to false,
                            maxWidth.toFloat() - size to true
                        ),
                        thresholds = { from, to ->
                            if (from == true) {
                                //锚点间吸附动画的阈值，滑块需移动超过 50% 才会自动吸附到关闭状态
                                FractionalThreshold(0.5f)
                            } else {
                                FractionalThreshold(0.7f)
                            }
                        }
                    )
                    .background(Color.Blue)
            )
        }
    }

    /**
     * Transformer双指拖动、缩放与旋转
     */
    @Composable
    fun TransformerView() {
//        Box(modifier = Modifier.transformable())
    }

}
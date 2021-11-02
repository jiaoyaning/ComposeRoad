package com.jyn.composeroad.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.jyn.composeroad.base.BaseActivity
import com.jyn.composeroad.ui.theme.ComposeRoadTheme

/*
 * Jetpack-Compose 学习笔记（三）—— Compose 的自定义“View”
 * https://blog.csdn.net/lbs458499563/article/details/120212671
 */
class ViewActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeRoadTheme { ViewDraw() } }
    }

    @Preview(showBackground = true)
    @Composable
    fun ViewDraw() {
        Text("测试", Modifier.drawWithContent {
            drawRect(Color.Yellow)
            drawContent()
        })
    }
}
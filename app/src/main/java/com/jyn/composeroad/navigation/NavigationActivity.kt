package com.jyn.composeroad.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.jyn.composeroad.ui.theme.ComposeRoadTheme

/*
 * 官网：https://developer.android.google.cn/jetpack/compose/navigation
 * demo： https://android--code.blogspot.com/2021/10/jetpack-compose-navigation-object.html
 */
class NavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { ComposeRoadTheme { TextView() } }
    }

    @Composable
    fun TextView() {
        Text("这是一个测试")
    }
}
package com.jyn.composeroad.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/*
 * Button 的 "进化之旅" | 我们是如何设计 Compose API 的
 * https://juejin.cn/post/7013567591796965407
 */
@Composable
fun Btn(text: String, des: String? = null, onClick: () -> Unit) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        if (!des.isNullOrEmpty()) Text(text = des)
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = text)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BtnPreview() {
    Btn("这是一个预览按钮", "这是一个描述字符") {}
}
package com.jyn.composeroad.base

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun Btn(onClick: () -> Unit, text: String, des: String? = null) {
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
    Btn({}, "这是一个预览按钮", "这是一个描述字符")
}
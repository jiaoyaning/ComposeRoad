package com.yibochi.composeroad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
 * Compose博物馆
 * https://docs.compose.net.cn
 *
 * 官方文档
 * https://developer.android.google.cn/courses/pathways/compose
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewTest()
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
}
package com.jyn.composeroad.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.databinding.DataBindingUtil
import com.jyn.composeroad.R
import com.jyn.composeroad.base.BaseActivity
import com.jyn.composeroad.databinding.ActivityViewBinding

/*
 * https://developer.android.com/jetpack/compose/interop/interop-apis?hl=zh-cn
 */
class ViewActivity : BaseActivity() {

    private val binding: ActivityViewBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_view)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //传统View嵌套Compose
        binding.composeView01.setContent {
            Text(text = "这是一个Compose Text")
        }

        //Compose嵌套传统View
        binding.composeView02.setContent {
            Column(modifier = Modifier.semantics { testTagsAsResourceId = true }) {
                Text(text = "这是一个Compose Text 在上面👆")

                AndroidView(
                    factory = { context ->
                        TextView(context).apply { text = "我是原生TextView，在中间" }
                    },
                    modifier = Modifier
                        .height(40.dp)
                        .align(CenterHorizontally),
                    update = {
                        //重组时会调用
                    }
                )

                Text(
                    modifier = Modifier.testTag("Text1"),
                    text = "这是一个Compose Text 在下面👇"
                )

                val test = remember { mutableStateOf("这是一个Compose 输入框") }
                TextField(
                    modifier = Modifier.testTag(test.value),
                    value = test.value,
                    onValueChange = { test.value = it })

                Spacer(modifier = Modifier.height(8.dp))

                val test2 = remember { mutableStateOf("这是一个Compose 输入框2") }
                OutlinedTextField(
                    modifier = Modifier.testTag(test2.value),
                    value = test2.value,
                    onValueChange = { test2.value = it })

                Spacer(modifier = Modifier.height(8.dp))

                val openDialog = remember { mutableStateOf(false) }
                Button(onClick = { openDialog.value = true }) {
                    Text(modifier = Modifier.testTag("弹窗"), text = "弹窗")
                }
                if (openDialog.value) {
                    AlertDialog(
                        onDismissRequest = { openDialog.value = false },
                        title = { Text(text = "测试") },
                        text = { Text(text = "这是text") },
                        confirmButton = {
                            TextButton(onClick = { openDialog.value = false }) {
                                Text(
                                    "确认",
                                    fontWeight = FontWeight.W700,
                                    style = MaterialTheme.typography.button
                                )
                            }
                        })
                }
            }
        }
    }
}
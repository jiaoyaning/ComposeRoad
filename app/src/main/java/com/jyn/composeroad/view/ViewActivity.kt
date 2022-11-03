package com.jyn.composeroad.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
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
            Column {
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

                Text(text = "这是一个Compose Text 在下面👇")

                val test = remember { mutableStateOf("这是一个Compose 输入框") }
                TextField(value = test.value, onValueChange = { test.value = it })
            }
        }
    }
}
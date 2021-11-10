package com.jyn.composeroad.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jyn.composeroad.R
import com.jyn.composeroad.databinding.ActivityViewBinding

/**
 * https://developer.android.com/jetpack/compose/interop/interop-apis?hl=zh-cn
 */
class ViewActivity : AppCompatActivity() {
    val binding: ActivityViewBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
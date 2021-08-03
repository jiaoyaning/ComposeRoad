package com.jyn.composeroad.base

import android.content.Intent
import androidx.activity.ComponentActivity

open class BaseActivity : ComponentActivity() {
    fun <T> goto(cls: Class<T>) = startActivity(Intent(this, cls))
}
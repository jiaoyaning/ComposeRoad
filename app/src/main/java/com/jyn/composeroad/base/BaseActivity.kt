package com.jyn.composeroad.base

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity

open class BaseActivity : ComponentActivity() {
    fun <T> goto(cls: Class<T>) = startActivity(Intent(this, cls))
    fun toast(text: CharSequence) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
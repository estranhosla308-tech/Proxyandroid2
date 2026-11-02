package com.proxyandroid.panel
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tv = TextView(this)
        tv.text = "Se voce esta lendo isso, NAO crashou!"
        tv.textSize = 24f
        tv.setPadding(50,200,50,50)
        setContentView(tv)
    }
}

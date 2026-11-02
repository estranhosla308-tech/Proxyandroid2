package com.proxyandroid.panel

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {

    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv = TextView(this)
        tv.textSize = 18f
        tv.setPadding(60, 200, 60, 60)
        setContentView(tv)

        tv.setOnClickListener {
            updateStatus()
            tryRequest()
        }

        updateStatus()
        tryRequest()
    }

    override fun onResume() {
        super.onResume()
        updateStatus()
    }

    private fun tryRequest() {
        try {
            if (!Shizuku.pingBinder()) return
            if (Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
                Shizuku.requestPermission(1000)
            }
        } catch (e: Exception) {
        }
    }

    private fun updateStatus() {
        val text = try {
            if (!Shizuku.pingBinder()) {
                "Shizuku NAO esta rodando\n\n1. Abra o app Shizuku\n2. Inicie via Wireless ADB\n3. Volte aqui e toque"
            } else if (Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
                "Shizuku rodando, SEM permissao\n\nToque para pedir"
            } else {
                "ProxyAndroid OK!\nShizuku funcionando!"
            }
        } catch (e: Exception) {
            "Erro: " + e.message
        }
        tv.text = text
    }
}

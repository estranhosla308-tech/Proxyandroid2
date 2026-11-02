package com.proxyandroid.panel

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView

    private val permissionListener = Shizuku.OnRequestPermissionResultListener { requestCode, grantResult ->
        runOnUiThread { updateStatus() }
    }

    private val binderReceivedListener = Shizuku.OnBinderReceivedListener {
        runOnUiThread { updateStatus() }
    }

    private val binderDeadListener = Shizuku.OnBinderDeadListener {
        runOnUiThread { updateStatus() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        statusText = findViewById(R.id.status_text)

        try {
            Shizuku.addRequestPermissionResultListener(permissionListener)
            Shizuku.addBinderReceivedListenerSticky(binderReceivedListener)
            Shizuku.addBinderDeadListener(binderDeadListener)
        } catch (e: Exception) {}

        updateStatus()

        statusText.setOnClickListener {
            updateStatus()
            try {
                if (Shizuku.pingBinder()) {
                    if (Shizuku.checkSelfPermission() != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                        Shizuku.requestPermission(1000)
                    }
                }
            } catch (e: Exception) {
                statusText.text = "Erro: ${e.message}\n\nToque para tentar de novo"
            }
        }
    }

    private fun updateStatus() {
        try {
            if (!Shizuku.pingBinder()) {
                statusText.text = "Shizuku NAO esta rodando\n\n1. Abra o app Shizuku\n2. Inicie com Wireless ADB ou Root\n3. Volte aqui e toque na tela"
                return
            }
            if (Shizuku.checkSelfPermission() == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                statusText.text = "Shizuku OK! Permissao concedida\n\nApp funcionando!"
            } else {
                statusText.text = "Shizuku rodando, mas SEM permissao\n\nToque aqui para pedir permissao"
            }
        } catch (e: Exception) {
            statusText.text = "Shizuku nao esta rodando\n\n${e.message}\n\nToque para atualizar"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            Shizuku.removeRequestPermissionResultListener(permissionListener)
            Shizuku.removeBinderReceivedListener(binderReceivedListener)
            Shizuku.removeBinderDeadListener(binderDeadListener)
        } catch (e: Exception) {}
    }
}

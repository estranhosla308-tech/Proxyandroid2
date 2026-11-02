package com.proxyandroid.panel // <-- Mantenha o nome do pacote que já está no seu arquivo

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {

    // Código para identificar o pedido de permissão
    private val SHIZUKU_PERMISSION_REQUEST_CODE = 1001

    // 1. Listener: Avisa quando o Binder do Shizuku chegar no seu app
    private val binderReceivedListener = Shizuku.OnBinderReceivedListener {
        // O Shizuku está pronto. Agora sim podemos verificar a permissão.
        checkAndRequestShizukuPermission()
    }

    // 2. Listener: Recebe o resultado quando o usuário permite ou nega
    private val permissionResultListener = Shizuku.OnRequestPermissionResultListener { requestCode, grantResult ->
        if (requestCode == SHIZUKU_PERMISSION_REQUEST_CODE) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida. Coloque aqui a ação que você quer executar.
                println("Shizuku: Permissão concedida!")
            } else {
                // Permissão negada.
                println("Shizuku: Permissão negada.")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)  Se tiver layout, descomente essa linha

        // 3. Registrar os listeners
        Shizuku.addBinderReceivedListenerSticky(binderReceivedListener)
        Shizuku.addRequestPermissionResultListener(permissionResultListener)
    }

    private fun checkAndRequestShizukuPermission() {
        // Verifica se a versão do Shizuku é suportada
        if (Shizuku.isPreV11) {
            println("Shizuku: Versão antiga não suportada.")
            return
        }

        // Se já tem permissão, executa a ação
        if (Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) {
            println("Shizuku: Já temos permissão!")
        } else {
            // Pede a permissão (abre o popup do Shizuku)
            Shizuku.requestPermission(SHIZUKU_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // 4. Remover listeners para evitar vazamento de memória
        Shizuku.removeBinderReceivedListener(binderReceivedListener)
        Shizuku.removeRequestPermissionResultListener(permissionResultListener)
    }
}
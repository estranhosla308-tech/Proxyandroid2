9package com.proxyandroid.panel

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import rikka.shizuku.Shizuku

class MainActivity : AppCompatActivity() {

    private lateinit var tv: TextView
    private val reqCode = 1000

    private val permListener = Shizuku.OnRequestPermission

kt

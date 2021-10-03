package com.example.yarmarka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yarmarka.utils.fm

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Yarmarka)
        setContentView(R.layout.activity_main)

        fm = supportFragmentManager
    }
}
package ru.gb.materialapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.gb.materialapp.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val themeSettings = "THEME_SETTINGS"
    private val file = "settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = this.getSharedPreferences(this.file, MODE_PRIVATE)
        val theme = sharedPreferences.getInt(themeSettings, R.style.Theme_MaterialApp)
        setTheme(theme)
    }
}
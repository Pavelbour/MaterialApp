package ru.gb.materialapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.gb.materialapp.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val themeSettings = "THEME_SETTINGS"
    private val file = "settings"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_activity_fragment_container,
            MainFragment())
            .commit()

        val sharedPreferences = this.getSharedPreferences(this.file, MODE_PRIVATE)
        val theme = sharedPreferences.getInt(themeSettings, R.style.Theme_MaterialApp)
        setTheme(theme)

        val bottomNavView: BottomNavigationView = findViewById(R.id.main_fragment_bottom_app_bar)

        bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bottom_appbar_settings -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container,
                            SettingsFragment())
                        .addToBackStack("")
                        .commit()
                }
                R.id.menu_bottom_appbar_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container,
                            MainFragment())
                        .addToBackStack("")
                        .commit()
                }
                R.id.menu_bottom_appbar_wikipedia -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_activity_fragment_container,
                            WikiFragment())
                        .addToBackStack("")
                        .commit()
                }
            }
            true
        }
    }
}
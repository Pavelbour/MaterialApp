package ru.gb.materialapp.ui

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.gb.materialapp.R
import ru.gb.materialapp.databinding.FragmentSettingsBinding

class SettingsFragment: Fragment(R.layout.fragment_settings) {
    private val themeSettings = "THEME_SETTINGS"
    private val file = "settings"
    private var  theme: Int = R.style.Theme_MaterialApp

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSettingsBinding.bind(view)

        binding.settingsClairTheme.setOnClickListener {
            this.theme = R.style.Theme_MaterialApp
        }

        binding.settingsDarkTheme.setOnClickListener {
            this.theme = R.style.Theme_MaterialApp_Dark
        }

        binding.settingsSaveButton.setOnClickListener {
            val sharedPreferences = context?.getSharedPreferences(this.file, MODE_PRIVATE)
            val editor = sharedPreferences?.edit()
            editor?.putInt(this.themeSettings, this.theme)
            editor?.commit()

           activity?.recreate()

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_activity_fragment_container,
                MainFragment())
                ?.addToBackStack("")
                ?.commit()

        }
    }
}
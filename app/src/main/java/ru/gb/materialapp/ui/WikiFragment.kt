package ru.gb.materialapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import ru.gb.materialapp.R
import ru.gb.materialapp.databinding.FragmentWikiBinding

class WikiFragment: Fragment(R.layout.fragment_wiki) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentWikiBinding.bind(view)

        binding.fragmentWikiContainer.webViewClient = WebViewClient()
        binding.fragmentWikiContainer.loadUrl("https://en.wikipedia.org")
    }
}
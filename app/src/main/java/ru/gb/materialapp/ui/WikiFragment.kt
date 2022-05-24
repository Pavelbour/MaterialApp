package ru.gb.materialapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.gb.materialapp.R
import ru.gb.materialapp.databinding.FragmentWikiBinding

class WikiFragment: Fragment(R.layout.fragment_wiki) {
    private val viewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private val baseUrl = "https://en.wikipedia.org/"
    private var url = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentWikiBinding.bind(view)

        if (savedInstanceState?.containsKey("search") == true) {
            val search = savedInstanceState?.get("search")
            this.url = "w/index.php?search=${search}"
        } else {
            this.url = ""
        }

        binding.fragmentWikiContainer.webViewClient = WebViewClient()
        binding.fragmentWikiContainer.loadUrl(this.baseUrl + this.url)
    }
}
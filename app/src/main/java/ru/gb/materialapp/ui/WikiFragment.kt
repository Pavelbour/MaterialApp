package ru.gb.materialapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.gb.materialapp.R
import ru.gb.materialapp.databinding.FragmentWikiBinding

class WikiFragment : Fragment(R.layout.fragment_wiki) {
    private val searchViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    private val baseUrl = "https://en.wikipedia.org/"
    private var url = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentWikiBinding.bind(view)

        binding.fragmentWikiContainer.webViewClient = WebViewClient()

        searchViewModel.search.observe(viewLifecycleOwner) {
            if (it.equals("")) {
                this.url = ""
            } else {
                this.url = "w/index.php?search=${it}"
            }
            binding.fragmentWikiContainer.loadUrl(this.baseUrl + this.url)
        }
    }
}
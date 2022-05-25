package ru.gb.materialapp.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.flow.collect
import ru.gb.materialapp.R
import ru.gb.materialapp.databinding.FragmentMainBinding
import ru.gb.materialapp.domain.NasaRepositoryImpl

class MainFragment: Fragment(R.layout.fragment_main) {
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(NasaRepositoryImpl())
    }

    private val searchViewModel by lazy {
        ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.requestPictureOfTheDay()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        ObjectAnimator.ofFloat(binding.mainFragmentTextInput, "translationX", 0f).apply {
            duration = 3000L
            start()
        }

        binding.mainFragmentTextInput.setEndIconOnClickListener {
            searchViewModel.setSearch(binding.mainFragmentTextInputField.text.toString())

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.main_activity_fragment_container,
                WikiFragment())
                ?.addToBackStack("")
                ?.commit()
//            EditDialog().show(parentFragmentManager, "tag")
        }

        val behavior: BottomSheetBehavior<LinearLayout> = BottomSheetBehavior.from(binding.mainFragmentBottomSheet)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED

        behavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.loading.collect {
                binding.mainFragmentProgress.visibility = if (it) View.VISIBLE else View.GONE
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.error.collect {
                Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.image.collect { url ->
                url?.let {
                    binding.mainFragmentImage.load(it)
                }
            }
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.explanation.collect { explanation ->
                explanation.let {
                    binding.mainFragmentImageDescription.text = it
                }
            }
        }
    }
}
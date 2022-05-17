package ru.gb.materialapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.gb.materialapp.domain.NasaRepository
import java.io.IOException

class MainViewModel(private val repository: NasaRepository): ViewModel() {
    private val _loading = MutableStateFlow(false)
    val loading: Flow<Boolean> = _loading

    private val _image: MutableStateFlow<String?> = MutableStateFlow(null)
    val image: Flow<String?> = _image

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: Flow<String> = _error

    private val _explanation: MutableSharedFlow<String> = MutableSharedFlow()
    val explanation: Flow<String> = _explanation

    fun requestPictureOfTheDay() {
        viewModelScope.launch {
            _loading.emit(true)
            try {
                val url = repository.pictureOfTheDay().url
                _image.emit(url)
                val explanation = repository.pictureOfTheDay().explanation
                _explanation.emit(explanation)
            } catch (exc: IOException) {
                _error.emit("Network error")
            }
            _loading.emit(false)
        }
    }
}

class MainViewModelFactory(val repository: NasaRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(repository) as T
}
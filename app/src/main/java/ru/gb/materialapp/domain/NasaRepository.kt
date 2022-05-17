package ru.gb.materialapp.domain

import ru.gb.materialapp.api.PictureOfTheDayResponse

interface NasaRepository {
    suspend fun pictureOfTheDay(): PictureOfTheDayResponse
}
package ru.gb.materialapp.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.gb.materialapp.api.NasaApi
import ru.gb.materialapp.api.PictureOfTheDayResponse

class NasaRepositoryImpl: NasaRepository {
    private val api = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.nasa.gov/")
            .client(OkHttpClient.Builder().apply {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
                .build()
            )
            .build()
            .create(NasaApi::class.java)

    override suspend fun pictureOfTheDay(): PictureOfTheDayResponse = api.pictureOfTheDay( "DEMO_KEY")
}
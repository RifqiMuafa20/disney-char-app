package com.D121211063.aplikasilistkarakterdisney.data

import com.D121211063.aplikasilistkarakterdisney.network.CharactersApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val charactersRepository: CharactersRepository
}

class DefaultAppContainer : AppContainer {
//    private val BASE_URL = "https://api.disneyapi.dev/character/data/"
    private val BASE_URL = "https://my-json-server.typicode.com/RifqiMuafa20/disney-character-api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val retrofitService: CharactersApiService by lazy {
        retrofit.create(CharactersApiService::class.java)
    }

    override val charactersRepository: CharactersRepository by lazy {
        DefaultCharactersRepository(retrofitService)
    }
}
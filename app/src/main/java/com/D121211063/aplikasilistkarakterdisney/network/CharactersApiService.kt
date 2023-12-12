package com.D121211063.aplikasilistkarakterdisney.network

import com.D121211063.aplikasilistkarakterdisney.model.Characterm
import retrofit2.http.GET

interface CharactersApiService {
    @GET("data")
    suspend fun getCharacters(): List<Characterm>
}
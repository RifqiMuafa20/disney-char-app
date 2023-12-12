package com.D121211063.aplikasilistkarakterdisney.data

import com.D121211063.aplikasilistkarakterdisney.model.Characterm
import com.D121211063.aplikasilistkarakterdisney.network.CharactersApiService

interface CharactersRepository {
    suspend fun getCharacters(): List<Characterm>
}

class DefaultCharactersRepository(
    private val charactersApiService: CharactersApiService
) : CharactersRepository {
    override suspend fun getCharacters(): List<Characterm> = charactersApiService.getCharacters()
}
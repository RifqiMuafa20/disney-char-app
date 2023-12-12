package com.D121211063.aplikasilistkarakterdisney.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.D121211063.aplikasilistkarakterdisney.CharactersApplication
import com.D121211063.aplikasilistkarakterdisney.data.CharactersRepository
import com.D121211063.aplikasilistkarakterdisney.model.Characterm
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface CharactersUiState {
    data class Success(val characters: List<Characterm>) : CharactersUiState
    object Loading : CharactersUiState
    object Error : CharactersUiState
}

class CharactersViewModel(private val charactersRepository: CharactersRepository) : ViewModel() {
    var charactersUiState: CharactersUiState by mutableStateOf(CharactersUiState.Loading)
        private set

    init {
        getCharacters()
    }

    fun getCharacters() {
        viewModelScope.launch {
            charactersUiState = CharactersUiState.Loading
            charactersUiState = try {
                CharactersUiState.Success(charactersRepository.getCharacters())
            } catch (e: IOException) {
                CharactersUiState.Error
            } catch (e: HttpException) {
                CharactersUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as CharactersApplication)
                val charactersRepository = application.container.charactersRepository
                CharactersViewModel(charactersRepository = charactersRepository)
            }
        }
    }
}
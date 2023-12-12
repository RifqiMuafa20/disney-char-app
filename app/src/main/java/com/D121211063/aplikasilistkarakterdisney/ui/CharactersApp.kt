package com.D121211063.aplikasilistkarakterdisney.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.D121211063.aplikasilistkarakterdisney.R
import com.D121211063.aplikasilistkarakterdisney.ui.screens.CharactersViewModel
import com.D121211063.aplikasilistkarakterdisney.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val charactersViewModel: CharactersViewModel =
                viewModel(factory = CharactersViewModel.Factory)
            HomeScreen(
                charactersUiState = charactersViewModel.charactersUiState,
                retryAction = charactersViewModel::getCharacters,
                modifier = Modifier.fillMaxSize(),
                contentPadding = it
            )
        }
    }
}
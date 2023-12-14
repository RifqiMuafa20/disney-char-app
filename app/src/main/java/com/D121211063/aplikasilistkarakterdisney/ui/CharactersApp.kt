package com.D121211063.aplikasilistkarakterdisney.ui

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.D121211063.aplikasilistkarakterdisney.R
import com.D121211063.aplikasilistkarakterdisney.ui.screens.AboutPage
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
                    Row {
                        Text(
                            text = ("Disney Characters"),
                            style = MaterialTheme.typography.headlineMedium,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        navigateToAboutPage()
                    }
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

@Composable
fun navigateToAboutPage(context: Context = LocalContext.current) {
    Image(
        painter = painterResource(id = R.drawable.foto_profile),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(end = 16.dp)
            .size(45.dp)
            .clip(CircleShape)
            .border(1.dp, Color.LightGray, CircleShape)
            .clickable {
                val intent = Intent(context, AboutPage::class.java)
                context.startActivity(intent)
            }
    )
}

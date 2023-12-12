package com.D121211063.aplikasilistkarakterdisney.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211063.aplikasilistkarakterdisney.R
import com.D121211063.aplikasilistkarakterdisney.model.Characterm
import com.D121211063.aplikasilistkarakterdisney.ui.theme.AplikasiListKarakterDisneyTheme

@Composable
fun HomeScreen(
    charactersUiState: CharactersUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    when (charactersUiState) {
        is CharactersUiState.Loading -> LoadingScreen(modifier.size(200.dp))
        is CharactersUiState.Success ->
            CharacterListScreen(
                characters = charactersUiState.characters,
                modifier = modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                    ),
                contentPadding = contentPadding
            )
        else -> ErrorScreen(retryAction, modifier)
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading),
        modifier = modifier
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(stringResource(R.string.loading_failed))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun CharacterCard(character: Characterm, context: Context, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable {
                context.startActivity(
                    Intent(context, DetailCharacterActivity::class.java).apply {
                        putExtra("character", character)
                    }
                )
            },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.team_title, character?.name ?: stringResource(R.string.team_title), character?.films ?: stringResource(R.string.team_title)),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.padding_medium)),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(character?.imageUrl ?: stringResource(R.string.team_title))
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img)
            )
            Text(
                text = character?.name ?: stringResource(R.string.team_title),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}

@Composable
private fun CharacterListScreen(
    characters: List<Characterm>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(
            items = characters,
            key = { character ->
                character?.id  ?: Long.MIN_VALUE
            }
        ) {character ->
            CharacterCard(character = character, context = LocalContext.current, modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    AplikasiListKarakterDisneyTheme {
        LoadingScreen(
            Modifier
                .fillMaxSize()
                .size(200.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    AplikasiListKarakterDisneyTheme {
        ErrorScreen({}, Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true)
@Composable
fun CharacterListScreenPreview() {
    AplikasiListKarakterDisneyTheme {
        val mockCharacterm = List(15){
            Characterm(
                allies = listOf("Hercules", "Philoctetes"),
                createdAt = "2023-12-11T12:34:56.789Z",
                enemies = listOf("Hades", "Pain", "Panic"),
                films = listOf("Hercules"),
                id = it,
                imageUrl = "https://example.com/achilles.png",
                name = "Achilles",
                parkAttractions = listOf("The Little Mermaid - Ariel's Undersea Adventure"),
                shortFilms = listOf("Philoctetes and the Olympians"),
                sourceUrl = "https://disney.fandom.com/wiki/Achilles_(Hercules)",
                tvShows = listOf("Hercules: The Animated Series"),
                updatedAt = "2023-12-11T12:35:00.123Z",
                url = "https://api.disneyapi.dev/characters/112",
                v = 0,
                videoGames = listOf("Kingdom Hearts", "Disney Infinity")
            )
        }
        CharacterListScreen(
            mockCharacterm, Modifier.fillMaxSize()
        )
    }
}
package com.D121211063.aplikasilistkarakterdisney.ui.screens

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211063.aplikasilistkarakterdisney.model.Characterm
import com.D121211063.aplikasilistkarakterdisney.ui.theme.AplikasiListKarakterDisneyTheme
import com.D121211063.aplikasilistkarakterdisney.ui.theme.md_theme_dark_surfaceVariant

class DetailCharacterActivity : AppCompatActivity() {

    private var character: Characterm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = intent.getParcelableExtra("character")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContent{
            AplikasiListKarakterDisneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    private fun DetailScreen() {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_dark_surfaceVariant),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(character?.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(bottom = 16.dp, top = 50.dp)
                        .size(240.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.LightGray, CircleShape)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = character?.name.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
                Text(
                    text = "from " + character?.films?.get(0).toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp, bottom = 20.dp),
                    color = Color.LightGray
                )
                ShareButton(link = character?.sourceUrl ?: "https://www.disney.com/") {
                    shareLink(character?.sourceUrl.toString())
                }
            }
        }
    }

    @Composable
    fun ShareButton(link: String, onClick: () -> Unit) {
        Button(
            modifier = Modifier
                .width(200.dp)
                .padding(horizontal = 16.dp, vertical = 28.dp)
                .border(2.dp, Color.LightGray, CircleShape),
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White,
            )
        ) {
            Text("Bagikan link", color = Color.White, fontSize = 16.sp)
        }
    }

    fun shareLink(link: String) {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    @Preview
    @Composable
    fun DetailScreenPreview() {
        DetailScreen()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

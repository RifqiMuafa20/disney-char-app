package com.D121211063.aplikasilistkarakterdisney.ui.screens

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.D121211063.aplikasilistkarakterdisney.R
import com.D121211063.aplikasilistkarakterdisney.ui.theme.AplikasiListKarakterDisneyTheme
import com.D121211063.aplikasilistkarakterdisney.ui.theme.md_theme_dark_surfaceVariant

class AboutPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContent{
            AplikasiListKarakterDisneyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    UserProfileScreen(namaLengkap = "Muh Rifqi Mu'afa", email = "D121211063", photo = painterResource(id = R.drawable.foto_profile))
                }
            }
        }
    }
    @Composable
    fun UserProfileScreen( namaLengkap: String, email: String, photo: Painter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(md_theme_dark_surfaceVariant),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = photo,
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
                text = namaLengkap,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray
            )
            Text(
                text = email,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp, bottom = 20.dp),
                color = Color.LightGray
            )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    @Preview
    @Composable
    fun AboutPagePreview() {
        UserProfileScreen(namaLengkap = "Muh Rifqi Mu'afa", email = "D121211063", photo = painterResource(id = R.drawable.foto_profile))
    }
}


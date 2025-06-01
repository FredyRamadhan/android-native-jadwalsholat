package com.kelompok9.jadwalsholat.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kelompok9.jadwalsholat.ui.theme.JadwalSholatTheme
import androidx.compose.material3.MaterialTheme as MaterialTheme1

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onTodayPrayerClick: () -> Unit = {},
    onShalatSunnahClick: () -> Unit = {},
    onLocationSettingsClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme1.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Selamat datang di Aplikasi Jadwal Sholat!",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme1.typography.headlineMedium,
                color = MaterialTheme1.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Silahkan pilih opsi di bawah untuk melihat lebih banyak:",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme1.typography.bodyLarge,
                color = MaterialTheme1.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = onTodayPrayerClick,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Jadwal Sholat Hari Ini")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onShalatSunnahClick,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Daftar Shalat Sunnah")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLocationSettingsClick,
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text(text = "Pengaturan Lokasi")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun HomeScreenPreview() {
    JadwalSholatTheme {
        HomeScreen()
    }
}
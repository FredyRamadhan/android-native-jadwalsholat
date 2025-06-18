package com.kelompok9.jadwalsholat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
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
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Jadwal Sholat",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme1.typography.headlineSmall,
                color = MaterialTheme1.colorScheme.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pilih menu di bawah:",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme1.typography.bodyMedium,
                color = MaterialTheme1.colorScheme.onBackground.copy(alpha = 0.7f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Prayer Times Button
            Card(
                onClick = onTodayPrayerClick,
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme1.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = null,
                        tint = MaterialTheme1.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Jadwal Sholat Hari Ini",
                        style = MaterialTheme1.typography.titleMedium,
                        color = MaterialTheme1.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Sunnah Prayers Button
            Card(
                onClick = onShalatSunnahClick,
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme1.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme1.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Daftar Shalat Sunnah",
                        style = MaterialTheme1.typography.titleMedium,
                        color = MaterialTheme1.colorScheme.onSurface
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Location Settings Button
            Card(
                onClick = onLocationSettingsClick,
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme1.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme1.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Pengaturan Lokasi",
                        style = MaterialTheme1.typography.titleMedium,
                        color = MaterialTheme1.colorScheme.onSurface
                    )
                }
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
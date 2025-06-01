package com.kelompok9.jadwalsholat.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kelompok9.jadwalsholat.ui.components.DateTimeDisplay
import com.kelompok9.jadwalsholat.ui.components.WaktuShalatItem
import com.kelompok9.jadwalsholat.ui.theme.JadwalSholatTheme
import com.kelompok9.jadwalsholat.ui.viewmodels.JadwalSholatViewModel

data class Prayer(
    val name: String,
    val time: String,
    val icon: ImageVector
)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarHariIniScreen(
    modifier: Modifier = Modifier,
    jadwalSholatViewModel: JadwalSholatViewModel = viewModel()
){
    val prayerTimes by jadwalSholatViewModel.prayerTimes.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Jadwal Sholat Hari Ini") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            DateTimeDisplay()
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Jadwal Sholat untuk tanggal ${java.time.LocalDate.now()}",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn {
                items(prayerTimes) { prayer ->
                    WaktuShalatItem(
                        prayerName = prayer.name,
                        prayerTime = prayer.time,
                        icon = prayer.icon
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, widthDp = 360)
@Composable
fun DaftarHariIniScreenPreview() {
    JadwalSholatTheme {
        DaftarHariIniScreen()
    }
}

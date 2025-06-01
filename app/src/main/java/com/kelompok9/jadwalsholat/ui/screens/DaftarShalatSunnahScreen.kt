package com.kelompok9.jadwalsholat.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kelompok9.jadwalsholat.ui.components.ShalatSunnahItem
import com.kelompok9.jadwalsholat.ui.theme.JadwalSholatTheme
import com.kelompok9.jadwalsholat.ui.viewmodels.JadwalSholatViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarShalatSunnahScreen(
    modifier: Modifier = Modifier,
    jadwalSholatViewModel: JadwalSholatViewModel = viewModel()
) {
    val shalatSunnahList by jadwalSholatViewModel.shalatSunnahList.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Daftar Shalat Sunnah") },
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

            Text(
                text = "Berikut adalah daftar shalat sunnah dan waktu pelaksanaannya:",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp)
            )


            LazyColumn {
                items(shalatSunnahList) { shalat ->
                    ShalatSunnahItem(shalatSunnah = shalat)
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun DaftarShalatSunnahScreenPreview() {
    JadwalSholatTheme {
        DaftarShalatSunnahScreen()
    }
}

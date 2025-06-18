package com.kelompok9.jadwalsholat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kelompok9.jadwalsholat.ui.components.ShalatSunnahItem
import com.kelompok9.jadwalsholat.ui.theme.JadwalSholatTheme
import com.kelompok9.jadwalsholat.data.models.ShalatSunnahDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DaftarShalatSunnahScreen(
    onShalatSunnahClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val shalatSunnahList = ShalatSunnahDataSource.shalatSunnahList

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
                .padding(horizontal = 12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Shalat Sunnah",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                items(shalatSunnahList) { shalat ->
                    ShalatSunnahItem(
                        shalatSunnah = shalat,
                        onClick = { onShalatSunnahClick(shalat.id) }
                    )
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

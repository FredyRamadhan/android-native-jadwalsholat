package com.kelompok9.jadwalsholat.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kelompok9.jadwalsholat.data.models.ShalatSunnahData
import com.kelompok9.jadwalsholat.data.models.ShalatSunnahDataSource
import com.kelompok9.jadwalsholat.ui.theme.JadwalSholatTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShalatSunnahDetailScreen(
    shalatSunnahId: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedShalatSunnah = ShalatSunnahDataSource.getShalatSunnahById(shalatSunnahId)
    
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = selectedShalatSunnah?.nama ?: "Detail Shalat Sunnah",
                        maxLines = 1
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        selectedShalatSunnah?.let { shalatSunnah ->
            ShalatSunnahDetailContent(
                shalatSunnah = shalatSunnah,
                modifier = Modifier.padding(innerPadding)
            )
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun ShalatSunnahDetailContent(
    shalatSunnah: ShalatSunnahData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
    ) {
        // Header Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = RoundedCornerShape(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = shalatSunnah.nama,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = shalatSunnah.deskripsi,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // Niat Section
        DetailSection(
            title = "Niat",
            content = shalatSunnah.niat,
            icon = Icons.Default.Star
        )
        
        Spacer(modifier = Modifier.height(8.dp))

        // Waktu Section
        DetailSection(
            title = "Waktu Pelaksanaan",
            content = shalatSunnah.waktu,
            icon = Icons.Default.AccessTime
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Manfaat Section
        DetailSection(
            title = "Manfaat",
            content = shalatSunnah.manfaat,
            icon = Icons.Default.Favorite
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Dalil Section
        DetailSection(
            title = "Dalil",
            content = shalatSunnah.dalil,
            icon = Icons.Default.MenuBook
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DetailSection(
    title: String,
    content: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = MaterialTheme.typography.bodySmall.lineHeight * 1.3
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShalatSunnahDetailScreenPreview() {
    JadwalSholatTheme {
        ShalatSunnahDetailContent(
            shalatSunnah = ShalatSunnahData(
                id = 1,
                nama = "Dhuha",
                deskripsi = "Shalat sunnah yang dilakukan setelah matahari terbit hingga sebelum waktu Dzuhur tiba.",
                niat = "Ushalli sunnatad dhuha rak'ataini lillahi ta'ala",
                waktu = "Setelah matahari terbit hingga sebelum Dzuhur (sekitar pukul 07:00 - 11:00)",
                manfaat = "Mendatangkan rezeki yang berkah, menambah kekuatan fisik, dan mendapat pahala yang besar dari Allah SWT.",
                dalil = "وَالضُّحَى وَاللَّيْلِ إِذَا سَجَى - Demi waktu Dhuha dan malam apabila telah sunyi (QS. Ad-Dhuha: 1-2)"
            )
        )
    }
}

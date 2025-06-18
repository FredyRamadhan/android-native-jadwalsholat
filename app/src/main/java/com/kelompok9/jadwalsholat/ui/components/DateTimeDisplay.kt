package com.kelompok9.jadwalsholat.ui.components

import android.os.Build
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kelompok9.jadwalsholat.ui.theme.JadwalSholatTheme
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun DateTimeDisplay(modifier: Modifier = Modifier) {

    var currentTime by remember { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        mutableStateOf(LocalTime.now())
    } else {
        TODO("VERSION.SDK_INT < O")
    }
    }


    LaunchedEffect(Unit) {
        while (true) {
            currentTime = LocalTime.now()
            delay(1000)
        }
    }

    val dateFormatter = remember { DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy") }
    val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm:ss") }

    val currentDate = LocalDate.now().format(dateFormatter)
    val formattedTime = currentTime.format(timeFormatter)

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Schedule,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = currentDate,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formattedTime,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 360)
@Composable
fun DateTimeDisplayPreview() {
    JadwalSholatTheme {
        DateTimeDisplay()
    }
}

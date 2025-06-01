package com.kelompok9.jadwalsholat.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import com.kelompok9.jadwalsholat.ui.screens.Prayer
import com.kelompok9.jadwalsholat.ui.components.ShalatSunnah
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel untuk mengelola data terkait jadwal sholat dan daftar shalat sunnah.
 */
class JadwalSholatViewModel : ViewModel() {

    private val _prayerTimes = MutableStateFlow(
        listOf(
            Prayer("Subuh", "04:22 WIB", Icons.Default.AccessTime),
            Prayer("Dzuhur", "11:37 WIB", Icons.Default.AccessTime),
            Prayer("Ashar", "14:59 WIB", Icons.Default.AccessTime),
            Prayer("Maghrib", "17:29 WIB", Icons.Default.AccessTime),
            Prayer("Isya", "18:44 WIB", Icons.Default.AccessTime)
        )
    )
    val prayerTimes: StateFlow<List<Prayer>> = _prayerTimes.asStateFlow()

    private val _shalatSunnahList = MutableStateFlow(
        listOf(
            ShalatSunnah(
                name = "Dhuha",
                description = "Shalat sunnah yang dilakukan setelah matahari terbit hingga sebelum Dzuhur.",
                timeOfDay = "Pagi Hari"
            ),
            ShalatSunnah(
                name = "Rawatib Qabliyah",
                description = "Shalat sunnah yang dilakukan sebelum shalat fardhu.",
                timeOfDay = "Sebelum Shalat Fardhu"
            ),
            ShalatSunnah(
                name = "Rawatib Ba'diyah",
                description = "Shalat sunnah yang dilakukan setelah shalat fardhu.",
                timeOfDay = "Setelah Shalat Fardhu"
            ),
            ShalatSunnah(
                name = "Tahajjud",
                description = "Shalat sunnah yang dilakukan di sepertiga malam terakhir.",
                timeOfDay = "Malam Hari"
            ),
            ShalatSunnah(
                name = "Witir",
                description = "Shalat sunnah penutup ibadah malam, ganjil rakaatnya.",
                timeOfDay = "Malam Hari"
            ),
            ShalatSunnah(
                name = "Tahiyatul Masjid",
                description = "Shalat sunnah yang dilakukan saat memasuki masjid sebelum duduk.",
                timeOfDay = "Saat Masuk Masjid"
            ),
            ShalatSunnah(
                name = "Istikharah",
                description = "Shalat sunnah untuk memohon petunjuk Allah dalam mengambil keputusan.",
                timeOfDay = "Kapan Saja (Saat Butuh Petunjuk)"
            )
        )
    )
    val shalatSunnahList: StateFlow<List<ShalatSunnah>> = _shalatSunnahList.asStateFlow()

}

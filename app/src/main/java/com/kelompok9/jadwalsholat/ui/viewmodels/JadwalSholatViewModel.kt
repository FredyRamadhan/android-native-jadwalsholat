package com.kelompok9.jadwalsholat.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.filled.Brightness3
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material.icons.filled.NightsStay
import com.kelompok9.jadwalsholat.ui.screens.Prayer
import com.kelompok9.jadwalsholat.ui.components.ShalatSunnah
import com.kelompok9.jadwalsholat.data.models.PrayerTimesState
import com.kelompok9.jadwalsholat.data.repository.PrayerTimesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

/**
 * ViewModel untuk mengelola data terkait jadwal sholat dan daftar shalat sunnah.
 */
class JadwalSholatViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PrayerTimesRepository(application)

    private val _prayerTimesState = MutableStateFlow<PrayerTimesState>(PrayerTimesState.Loading)
    val prayerTimesState: StateFlow<PrayerTimesState> = _prayerTimesState.asStateFlow()

    // Legacy support for existing UI
    private val _prayerTimes = MutableStateFlow<List<Prayer>>(emptyList())
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

    init {
        loadPrayerTimes()
        observeLocationChanges()
    }

    /**
     * Load prayer times based on selected location
     */
    fun loadPrayerTimes(date: LocalDate = LocalDate.now()) {
        viewModelScope.launch {
            _prayerTimesState.value = PrayerTimesState.Loading

            val selectedLocation = repository.getSelectedLocation()
            if (selectedLocation == null) {
                _prayerTimesState.value = PrayerTimesState.Error("Lokasi belum dipilih")
                return@launch
            }

            repository.getPrayerTimes(selectedLocation.cityId, date)
                .onSuccess { prayerData ->
                    val prayerTimesList = repository.convertToPrayerTimesList(prayerData.jadwal)

                    // Update new state
                    _prayerTimesState.value = PrayerTimesState.Success(
                        prayerTimes = prayerTimesList,
                        location = prayerData.lokasi,
                        date = prayerData.jadwal.tanggal
                    )

                    // Update legacy state for existing UI
                    _prayerTimes.value = prayerTimesList.map { prayerTime ->
                        Prayer(
                            name = prayerTime.name,
                            time = prayerTime.time,
                            icon = getPrayerIcon(prayerTime.name)
                        )
                    }
                }
                .onFailure { exception ->
                    _prayerTimesState.value = PrayerTimesState.Error(
                        exception.message ?: "Gagal memuat jadwal sholat"
                    )
                }
        }
    }

    /**
     * Observe location changes and reload prayer times
     */
    private fun observeLocationChanges() {
        viewModelScope.launch {
            repository.getSelectedLocationFlow().collect { location ->
                if (location != null) {
                    loadPrayerTimes()
                }
            }
        }
    }

    /**
     * Get appropriate icon for each prayer
     */
    private fun getPrayerIcon(prayerName: String) = when (prayerName.lowercase()) {
        "subuh" -> Icons.Default.WbSunny
        "dzuhur" -> Icons.Default.Brightness6
        "ashar" -> Icons.Default.Brightness3
        "maghrib" -> Icons.Default.Brightness3
        "isya" -> Icons.Default.NightsStay
        else -> Icons.Default.AccessTime
    }

}

package com.kelompok9.jadwalsholat.data.models

import com.google.gson.annotations.SerializedName

/**
 * Data models for MyQuran API responses
 */

// City list response
data class CityListResponse(
    val status: Boolean,
    val request: RequestInfo,
    val data: List<City>
)

data class City(
    val id: String,
    val lokasi: String
)

data class RequestInfo(
    val path: String
)

// Prayer times response
data class PrayerTimesResponse(
    val status: Boolean,
    val request: RequestInfo,
    val data: PrayerTimesData
)

data class PrayerTimesData(
    val id: Int,
    val lokasi: String,
    val daerah: String,
    val jadwal: PrayerSchedule
)

data class PrayerSchedule(
    val tanggal: String,
    val imsak: String,
    val subuh: String,
    val terbit: String,
    val dhuha: String,
    val dzuhur: String,
    val ashar: String,
    val maghrib: String,
    val isya: String,
    val date: String
)

// UI Models
data class PrayerTime(
    val name: String,
    val time: String,
    val arabicName: String = ""
)

data class LocationInfo(
    val cityId: String,
    val cityName: String,
    val region: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null
)

// Location state for UI
sealed class LocationState {
    object Loading : LocationState()
    data class Success(val location: LocationInfo) : LocationState()
    data class Error(val message: String) : LocationState()
}

// Prayer times state for UI
sealed class PrayerTimesState {
    object Loading : PrayerTimesState()
    data class Success(val prayerTimes: List<PrayerTime>, val location: String, val date: String) : PrayerTimesState()
    data class Error(val message: String) : PrayerTimesState()
}

// Cities state for UI
sealed class CitiesState {
    object Loading : CitiesState()
    data class Success(val cities: List<City>) : CitiesState()
    data class Error(val message: String) : CitiesState()
}

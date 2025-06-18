package com.kelompok9.jadwalsholat.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kelompok9.jadwalsholat.data.api.MyQuranApiService
import com.kelompok9.jadwalsholat.data.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

// DataStore extension
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Repository for handling prayer times and location data
 */
class PrayerTimesRepository(
    private val context: Context
) {
    
    companion object {
        private const val BASE_URL = "https://api.myquran.com/"
        private val SELECTED_CITY_ID = stringPreferencesKey("selected_city_id")
        private val SELECTED_CITY_NAME = stringPreferencesKey("selected_city_name")
        private val SELECTED_CITY_REGION = stringPreferencesKey("selected_city_region")
    }
    
    private val apiService: MyQuranApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyQuranApiService::class.java)
    }
    
    /**
     * Get all cities from API
     */
    suspend fun getAllCities(): Result<List<City>> {
        return try {
            val response = apiService.getAllCities()
            if (response.isSuccessful && response.body()?.status == true) {
                Result.success(response.body()!!.data)
            } else {
                Result.failure(Exception("Failed to fetch cities"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Get prayer times for a specific city and date
     */
    suspend fun getPrayerTimes(
        cityId: String,
        date: LocalDate = LocalDate.now()
    ): Result<PrayerTimesData> {
        return try {
            val response = apiService.getPrayerTimes(
                cityId = cityId,
                year = date.year,
                month = date.monthValue,
                day = date.dayOfMonth
            )
            if (response.isSuccessful && response.body()?.status == true) {
                Result.success(response.body()!!.data)
            } else {
                Result.failure(Exception("Failed to fetch prayer times"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    /**
     * Save selected location to DataStore
     */
    suspend fun saveSelectedLocation(cityId: String, cityName: String, region: String = "") {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CITY_ID] = cityId
            preferences[SELECTED_CITY_NAME] = cityName
            preferences[SELECTED_CITY_REGION] = region
        }
    }
    
    /**
     * Get selected location from DataStore
     */
    suspend fun getSelectedLocation(): LocationInfo? {
        val preferences = context.dataStore.data.first()
        val cityId = preferences[SELECTED_CITY_ID]
        val cityName = preferences[SELECTED_CITY_NAME]
        val region = preferences[SELECTED_CITY_REGION] ?: ""
        
        return if (cityId != null && cityName != null) {
            LocationInfo(cityId, cityName, region)
        } else {
            null
        }
    }
    
    /**
     * Get selected location as Flow
     */
    fun getSelectedLocationFlow(): Flow<LocationInfo?> {
        return context.dataStore.data.map { preferences ->
            val cityId = preferences[SELECTED_CITY_ID]
            val cityName = preferences[SELECTED_CITY_NAME]
            val region = preferences[SELECTED_CITY_REGION] ?: ""
            
            if (cityId != null && cityName != null) {
                LocationInfo(cityId, cityName, region)
            } else {
                null
            }
        }
    }
    
    /**
     * Convert API prayer schedule to UI prayer times list
     */
    fun convertToPrayerTimesList(schedule: PrayerSchedule): List<PrayerTime> {
        return listOf(
            PrayerTime("Subuh", schedule.subuh, "الفجر"),
            PrayerTime("Dzuhur", schedule.dzuhur, "الظهر"),
            PrayerTime("Ashar", schedule.ashar, "العصر"),
            PrayerTime("Maghrib", schedule.maghrib, "المغرب"),
            PrayerTime("Isya", schedule.isya, "العشاء")
        )
    }
}

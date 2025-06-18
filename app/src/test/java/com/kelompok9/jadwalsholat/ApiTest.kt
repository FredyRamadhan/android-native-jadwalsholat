package com.kelompok9.jadwalsholat

import com.kelompok9.jadwalsholat.data.api.MyQuranApiService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Simple test to verify API integration
 */
class ApiTest {
    
    private val apiService = Retrofit.Builder()
        .baseUrl("https://api.myquran.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MyQuranApiService::class.java)
    
    @Test
    fun testGetCities() = runBlocking {
        try {
            val response = apiService.getAllCities()
            println("Cities API Response: ${response.isSuccessful}")
            if (response.isSuccessful) {
                val cities = response.body()?.data
                println("Number of cities: ${cities?.size}")
                println("First few cities: ${cities?.take(5)}")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
    
    @Test
    fun testGetPrayerTimes() = runBlocking {
        try {
            val response = apiService.getPrayerTimes("1301", 2024, 12, 18)
            println("Prayer Times API Response: ${response.isSuccessful}")
            if (response.isSuccessful) {
                val prayerData = response.body()?.data
                println("Location: ${prayerData?.lokasi}")
                println("Prayer times: ${prayerData?.jadwal}")
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}

package com.kelompok9.jadwalsholat.data.api

import com.kelompok9.jadwalsholat.data.models.CityListResponse
import com.kelompok9.jadwalsholat.data.models.PrayerTimesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API service interface for MyQuran API
 */
interface MyQuranApiService {
    
    /**
     * Get all cities in Indonesia
     */
    @GET("v2/sholat/kota/semua")
    suspend fun getAllCities(): Response<CityListResponse>
    
    /**
     * Get prayer times for a specific city and date
     * @param cityId City ID from the cities list
     * @param year Year (e.g., 2024)
     * @param month Month (1-12)
     * @param day Day (1-31)
     */
    @GET("v2/sholat/jadwal/{cityId}/{year}/{month}/{day}")
    suspend fun getPrayerTimes(
        @Path("cityId") cityId: String,
        @Path("year") year: Int,
        @Path("month") month: Int,
        @Path("day") day: Int
    ): Response<PrayerTimesResponse>
}

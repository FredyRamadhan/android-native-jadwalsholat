package com.kelompok9.jadwalsholat.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * Helper class for location-related operations
 */
class LocationHelper(private val context: Context) {
    
    private val fusedLocationClient: FusedLocationProviderClient = 
        LocationServices.getFusedLocationProviderClient(context)
    
    /**
     * Check if location permissions are granted
     */
    fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }
    
    /**
     * Get current location
     */
    suspend fun getCurrentLocation(): Location? {
        if (!hasLocationPermission()) {
            throw SecurityException("Location permission not granted")
        }
        
        return suspendCancellableCoroutine { continuation ->
            val cancellationTokenSource = CancellationTokenSource()
            
            continuation.invokeOnCancellation {
                cancellationTokenSource.cancel()
            }
            
            try {
                fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    cancellationTokenSource.token
                ).addOnSuccessListener { location ->
                    continuation.resume(location)
                }.addOnFailureListener { exception ->
                    continuation.resumeWithException(exception)
                }
            } catch (e: SecurityException) {
                continuation.resumeWithException(e)
            }
        }
    }
    
    /**
     * Find nearest city based on coordinates
     * This is a simplified implementation - in a real app you might use
     * reverse geocoding or a more sophisticated algorithm
     */
    fun findNearestCityId(latitude: Double, longitude: Double): String {
        // For now, return Jakarta as default
        // In a real implementation, you would:
        // 1. Use reverse geocoding to get city name
        // 2. Match it with the cities from the API
        // 3. Return the appropriate city ID
        
        // Some major Indonesian cities with approximate coordinates
        val cities = mapOf(
            "1301" to Pair(-6.2088, 106.8456), // Jakarta
            "1219" to Pair(-6.9175, 107.6191), // Bandung
            "1638" to Pair(-7.2575, 112.7521), // Surabaya
            "1433" to Pair(-6.9667, 110.4167), // Semarang
            "1505" to Pair(-7.7956, 110.3695), // Yogyakarta
            "0228" to Pair(3.5952, 98.6722),   // Medan
            "0314" to Pair(-0.9471, 100.4172), // Padang
            "0412" to Pair(0.5333, 101.4500),  // Pekanbaru
            "0610" to Pair(-1.6101, 103.6131), // Jambi
            "0816" to Pair(-2.9761, 104.7754)  // Palembang
        )
        
        var nearestCityId = "1301" // Default to Jakarta
        var minDistance = Double.MAX_VALUE
        
        cities.forEach { (cityId, coords) ->
            val distance = calculateDistance(latitude, longitude, coords.first, coords.second)
            if (distance < minDistance) {
                minDistance = distance
                nearestCityId = cityId
            }
        }
        
        return nearestCityId
    }
    
    /**
     * Calculate distance between two coordinates using Haversine formula
     */
    private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val earthRadius = 6371.0 // Earth radius in kilometers
        
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        
        val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                Math.sin(dLon / 2) * Math.sin(dLon / 2)
        
        val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
        
        return earthRadius * c
    }
}

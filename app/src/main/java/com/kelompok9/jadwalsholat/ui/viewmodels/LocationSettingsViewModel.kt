package com.kelompok9.jadwalsholat.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kelompok9.jadwalsholat.data.models.*
import com.kelompok9.jadwalsholat.data.repository.PrayerTimesRepository
import com.kelompok9.jadwalsholat.utils.LocationHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for LocationSettingsScreen
 */
class LocationSettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PrayerTimesRepository(application)
    private val locationHelper = LocationHelper(application)

    private val _citiesState = MutableStateFlow<CitiesState>(CitiesState.Loading)
    val citiesState: StateFlow<CitiesState> = _citiesState.asStateFlow()

    private val _selectedLocation = MutableStateFlow<LocationInfo?>(null)
    val selectedLocation: StateFlow<LocationInfo?> = _selectedLocation.asStateFlow()
    
    init {
        loadSelectedLocation()
    }
    
    /**
     * Load all cities from API
     */
    fun loadCities() {
        viewModelScope.launch {
            _citiesState.value = CitiesState.Loading
            
            repository.getAllCities()
                .onSuccess { cities ->
                    _citiesState.value = CitiesState.Success(cities)
                }
                .onFailure { exception ->
                    _citiesState.value = CitiesState.Error(
                        exception.message ?: "Gagal memuat daftar kota"
                    )
                }
        }
    }
    
    /**
     * Load selected location from storage
     */
    private fun loadSelectedLocation() {
        viewModelScope.launch {
            repository.getSelectedLocationFlow().collect { location ->
                _selectedLocation.value = location
            }
        }
    }
    
    /**
     * Select a city and save to storage
     */
    fun selectCity(city: City) {
        viewModelScope.launch {
            // Extract region from city name if it contains "KAB." or "KOTA"
            val region = when {
                city.lokasi.startsWith("KAB.") -> "Kabupaten"
                city.lokasi.startsWith("KOTA") -> "Kota"
                else -> ""
            }
            
            repository.saveSelectedLocation(
                cityId = city.id,
                cityName = city.lokasi,
                region = region
            )
        }
    }
    
    /**
     * Use current device location
     */
    fun useCurrentLocation() {
        viewModelScope.launch {
            try {
                if (!locationHelper.hasLocationPermission()) {
                    // Permission not granted, fallback to Jakarta
                    repository.saveSelectedLocation(
                        cityId = "1301",
                        cityName = "KOTA JAKARTA",
                        region = "DKI JAKARTA"
                    )
                    return@launch
                }

                val location = locationHelper.getCurrentLocation()
                if (location != null) {
                    val nearestCityId = locationHelper.findNearestCityId(
                        location.latitude,
                        location.longitude
                    )

                    // Find the city name from the loaded cities
                    val currentCitiesState = _citiesState.value
                    val cities = (currentCitiesState as? CitiesState.Success)?.cities
                    val selectedCity = cities?.find { it.id == nearestCityId }

                    if (selectedCity != null) {
                        selectCity(selectedCity)
                    } else {
                        // Fallback to Jakarta if city not found
                        repository.saveSelectedLocation(
                            cityId = "1301",
                            cityName = "KOTA JAKARTA",
                            region = "DKI JAKARTA"
                        )
                    }
                } else {
                    // Location is null, fallback to Jakarta
                    repository.saveSelectedLocation(
                        cityId = "1301",
                        cityName = "KOTA JAKARTA",
                        region = "DKI JAKARTA"
                    )
                }
            } catch (e: SecurityException) {
                // Permission denied, fallback to Jakarta
                repository.saveSelectedLocation(
                    cityId = "1301",
                    cityName = "KOTA JAKARTA",
                    region = "DKI JAKARTA"
                )
            } catch (e: Exception) {
                // Any other error, fallback to Jakarta
                repository.saveSelectedLocation(
                    cityId = "1301",
                    cityName = "KOTA JAKARTA",
                    region = "DKI JAKARTA"
                )
            }
        }
    }
}

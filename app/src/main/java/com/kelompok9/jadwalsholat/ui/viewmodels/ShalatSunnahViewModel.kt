package com.kelompok9.jadwalsholat.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kelompok9.jadwalsholat.data.database.ShalatSunnahData
import com.kelompok9.jadwalsholat.data.repository.ShalatSunnahRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for Shalat Sunnah screens
 */
class ShalatSunnahViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository = ShalatSunnahRepository(application)
    
    private val _shalatSunnahList = MutableStateFlow<List<ShalatSunnahData>>(emptyList())
    val shalatSunnahList: StateFlow<List<ShalatSunnahData>> = _shalatSunnahList.asStateFlow()
    
    private val _selectedShalatSunnah = MutableStateFlow<ShalatSunnahData?>(null)
    val selectedShalatSunnah: StateFlow<ShalatSunnahData?> = _selectedShalatSunnah.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    init {
        loadShalatSunnahList()
    }
    
    /**
     * Load all shalat sunnah from database
     */
    private fun loadShalatSunnahList() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getAllShalatSunnah().collect { list ->
                _shalatSunnahList.value = list
                _isLoading.value = false
            }
        }
    }
    
    /**
     * Load specific shalat sunnah by ID
     */
    fun loadShalatSunnahById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val shalatSunnah = repository.getShalatSunnahById(id)
            _selectedShalatSunnah.value = shalatSunnah
            _isLoading.value = false
        }
    }
    
    /**
     * Clear selected shalat sunnah
     */
    fun clearSelectedShalatSunnah() {
        _selectedShalatSunnah.value = null
    }
}

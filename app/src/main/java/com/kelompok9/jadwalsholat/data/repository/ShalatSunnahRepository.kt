package com.kelompok9.jadwalsholat.data.repository

import android.content.Context
import com.kelompok9.jadwalsholat.data.database.AppDatabase
import com.kelompok9.jadwalsholat.data.database.ShalatSunnahData
import com.kelompok9.jadwalsholat.data.database.ShalatSunnahDao
import com.kelompok9.jadwalsholat.data.database.toShalatSunnahData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Repository for Shalat Sunnah data
 */
class ShalatSunnahRepository(context: Context) {
    
    private val dao: ShalatSunnahDao = AppDatabase.getDatabase(context).shalatSunnahDao()
    
    /**
     * Get all shalat sunnah as Flow
     */
    fun getAllShalatSunnah(): Flow<List<ShalatSunnahData>> {
        return dao.getAllShalatSunnah().map { entities ->
            entities.map { it.toShalatSunnahData() }
        }
    }
    
    /**
     * Get specific shalat sunnah by ID
     */
    suspend fun getShalatSunnahById(id: Int): ShalatSunnahData? {
        return dao.getShalatSunnahById(id)?.toShalatSunnahData()
    }
    
    /**
     * Check if database has data
     */
    suspend fun hasData(): Boolean {
        return dao.getCount() > 0
    }
}

package com.kelompok9.jadwalsholat.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for Shalat Sunnah
 */
@Dao
interface ShalatSunnahDao {
    
    @Query("SELECT * FROM shalat_sunnah ORDER BY nama ASC")
    fun getAllShalatSunnah(): Flow<List<ShalatSunnahEntity>>
    
    @Query("SELECT * FROM shalat_sunnah WHERE id = :id")
    suspend fun getShalatSunnahById(id: Int): ShalatSunnahEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShalatSunnah(shalatSunnah: ShalatSunnahEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllShalatSunnah(shalatSunnahList: List<ShalatSunnahEntity>)
    
    @Query("DELETE FROM shalat_sunnah")
    suspend fun deleteAll()
    
    @Query("SELECT COUNT(*) FROM shalat_sunnah")
    suspend fun getCount(): Int
}

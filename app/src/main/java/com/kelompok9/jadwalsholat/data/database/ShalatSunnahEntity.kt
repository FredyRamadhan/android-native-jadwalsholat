package com.kelompok9.jadwalsholat.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room entity for Shalat Sunnah data
 */
@Entity(tableName = "shalat_sunnah")
data class ShalatSunnahEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val deskripsi: String,
    val niat: String,
    val waktu: String,
    val manfaat: String,
    val dalil: String
)

/**
 * Data class for UI representation
 */
data class ShalatSunnahData(
    val id: Int,
    val nama: String,
    val deskripsi: String,
    val niat: String,
    val waktu: String,
    val manfaat: String,
    val dalil: String
)

/**
 * Extension function to convert entity to data class
 */
fun ShalatSunnahEntity.toShalatSunnahData(): ShalatSunnahData {
    return ShalatSunnahData(
        id = id,
        nama = nama,
        deskripsi = deskripsi,
        niat = niat,
        waktu = waktu,
        manfaat = manfaat,
        dalil = dalil
    )
}

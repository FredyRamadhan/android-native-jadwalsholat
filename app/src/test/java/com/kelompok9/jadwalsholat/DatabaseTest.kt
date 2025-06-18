package com.kelompok9.jadwalsholat

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kelompok9.jadwalsholat.data.database.AppDatabase
import com.kelompok9.jadwalsholat.data.database.ShalatSunnahDao
import com.kelompok9.jadwalsholat.data.database.ShalatSunnahEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

/**
 * Test for database functionality
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    
    private lateinit var database: AppDatabase
    private lateinit var dao: ShalatSunnahDao
    
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        
        dao = database.shalatSunnahDao()
    }
    
    @After
    fun teardown() {
        database.close()
    }
    
    @Test
    fun testInsertAndRetrieveShalatSunnah() = runBlocking {
        val testShalat = ShalatSunnahEntity(
            nama = "Test Shalat",
            deskripsi = "Test description",
            niat = "Test niat",
            waktu = "Test waktu",
            manfaat = "Test manfaat"
        )
        
        dao.insertShalatSunnah(testShalat)
        
        val allShalat = dao.getAllShalatSunnah().first()
        assertTrue("Database should contain the inserted shalat", allShalat.isNotEmpty())
        
        val retrievedShalat = allShalat.first()
        assertEquals("Test Shalat", retrievedShalat.nama)
        assertEquals("Test description", retrievedShalat.deskripsi)
    }
    
    @Test
    fun testGetShalatSunnahById() = runBlocking {
        val testShalat = ShalatSunnahEntity(
            id = 1,
            nama = "Test Shalat",
            deskripsi = "Test description",
            niat = "Test niat",
            waktu = "Test waktu",
            manfaat = "Test manfaat"
        )
        
        dao.insertShalatSunnah(testShalat)
        
        val retrievedShalat = dao.getShalatSunnahById(1)
        assertNotNull("Should retrieve shalat by ID", retrievedShalat)
        assertEquals("Test Shalat", retrievedShalat?.nama)
    }
}

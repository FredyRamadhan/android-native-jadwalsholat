package com.kelompok9.jadwalsholat.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Room database for the application
 */
@Database(
    entities = [ShalatSunnahEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun shalatSunnahDao(): ShalatSunnahDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Migration from version 1 to 2 (adding dalil column)
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE shalat_sunnah ADD COLUMN dalil TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                // Try to copy external database first
                copyDatabaseFromExternalFile(context)

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "jadwal_sholat_database"
                )
                .addMigrations(MIGRATION_1_2)
                .addCallback(DatabaseCallback())
                .build()
                INSTANCE = instance
                instance
            }
        }

        /**
         * Copy external database file to app's internal storage
         */
        private fun copyDatabaseFromExternalFile(context: Context) {
            try {
                val externalDbFile = File(context.getExternalFilesDir(null), "jadwal_sholat_database.db")
                val internalDbFile = File(context.getDatabasePath("jadwal_sholat_database").absolutePath)

                // If external file exists and internal doesn't, copy it
                if (externalDbFile.exists() && !internalDbFile.exists()) {
                    externalDbFile.inputStream().use { input ->
                        FileOutputStream(internalDbFile).use { output ->
                            input.copyTo(output)
                        }
                    }
                    android.util.Log.d("AppDatabase", "External database copied to internal storage")
                }
            } catch (e: IOException) {
                android.util.Log.e("AppDatabase", "Failed to copy external database", e)
            }
        }
    }
    
    /**
     * Callback to populate database with initial data
     */
    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.shalatSunnahDao())
                }
            }
        }
        
        /**
         * Populate database with initial shalat sunnah data
         */
        private suspend fun populateDatabase(dao: ShalatSunnahDao) {
            val initialData = listOf(
                ShalatSunnahEntity(
                    nama = "Dhuha",
                    deskripsi = "Shalat sunnah yang dilakukan setelah matahari terbit hingga sebelum waktu Dzuhur tiba.",
                    niat = "Ushalli sunnatad dhuha rak'ataini lillahi ta'ala",
                    waktu = "Setelah matahari terbit hingga sebelum Dzuhur (sekitar pukul 07:00 - 11:00)",
                    manfaat = "Mendatangkan rezeki yang berkah, menambah kekuatan fisik, dan mendapat pahala yang besar dari Allah SWT.",
                    dalil = "وَالضُّحَى وَاللَّيْلِ إِذَا سَجَى - Demi waktu Dhuha dan malam apabila telah sunyi (QS. Ad-Dhuha: 1-2)"
                ),
                ShalatSunnahEntity(
                    nama = "Rawatib Qabliyah",
                    deskripsi = "Shalat sunnah yang dikerjakan sebelum shalat fardhu sebagai persiapan menghadap Allah SWT.",
                    niat = "Ushalli sunnar rawatib qabliyah [nama shalat] rak'ataini lillahi ta'ala",
                    waktu = "Sebelum shalat fardhu (Subuh: 2 rakaat, Dzuhur: 4 rakaat, Maghrib: 2 rakaat, Isya: 2 rakaat)",
                    manfaat = "Menyempurnakan shalat fardhu, menambah pahala, dan mempersiapkan hati untuk menghadap Allah.",
                    dalil = "مَنْ صَلَّى اثْنَتَيْ عَشْرَةَ رَكْعَةً فِي يَوْمٍ وَلَيْلَةٍ بُنِيَ لَهُ بِهِنَّ بَيْتٌ فِي الْجَنَّةِ - HR. Muslim"
                ),
                ShalatSunnahEntity(
                    nama = "Rawatib Ba'diyah",
                    deskripsi = "Shalat sunnah yang dikerjakan setelah shalat fardhu sebagai penyempurna ibadah.",
                    niat = "Ushalli sunnar rawatib ba'diyah [nama shalat] rak'ataini lillahi ta'ala",
                    waktu = "Setelah shalat fardhu (Dzuhur: 2 rakaat, Maghrib: 2 rakaat, Isya: 2 rakaat)",
                    manfaat = "Menyempurnakan kekurangan dalam shalat fardhu dan menambah pahala di sisi Allah SWT.",
                    dalil = "مَا مِنْ عَبْدٍ مُسْلِمٍ يُصَلِّي لِلَّهِ كُلَّ يَوْمٍ ثِنْتَيْ عَشْرَةَ رَكْعَةً تَطَوُّعًا - HR. Muslim"
                ),
                ShalatSunnahEntity(
                    nama = "Tahajjud",
                    deskripsi = "Shalat sunnah yang dikerjakan di sepertiga malam terakhir, merupakan waktu yang sangat mulia.",
                    niat = "Ushalli sunnat tahajjud rak'ataini lillahi ta'ala",
                    waktu = "Sepertiga malam terakhir hingga sebelum waktu Subuh (sekitar pukul 02:00 - 04:00)",
                    manfaat = "Mendekatkan diri kepada Allah, doa lebih mudah dikabulkan, dan mendapat kedudukan terpuji di akhirat.",
                    dalil = "وَمِنَ اللَّيْلِ فَتَهَجَّدْ بِهِ نَافِلَةً لَّكَ - Dan pada sebagian malam hari bersembahyang tahajjudlah (QS. Al-Isra: 79)"
                ),
                ShalatSunnahEntity(
                    nama = "Witir",
                    deskripsi = "Shalat sunnah muakkad yang dikerjakan dengan jumlah rakaat ganjil sebagai penutup shalat malam.",
                    niat = "Ushalli sunnal witri [jumlah rakaat] lillahi ta'ala",
                    waktu = "Setelah shalat Isya hingga sebelum Subuh, lebih utama di akhir malam",
                    manfaat = "Menutup ibadah malam dengan sempurna dan mendapat pahala yang besar dari Allah SWT.",
                    dalil = "إِنَّ اللَّهَ وِتْرٌ يُحِبُّ الْوِتْرَ - Sesungguhnya Allah itu ganjil dan menyukai yang ganjil (HR. Muslim)"
                ),
                ShalatSunnahEntity(
                    nama = "Tahiyatul Masjid",
                    deskripsi = "Shalat sunnah yang dikerjakan ketika memasuki masjid sebagai penghormatan terhadap rumah Allah.",
                    niat = "Ushalli sunnat tahiyyatil masjid rak'ataini lillahi ta'ala",
                    waktu = "Setiap kali memasuki masjid, kecuali pada waktu yang dilarang untuk shalat",
                    manfaat = "Menghormati masjid sebagai rumah Allah dan mendapat pahala sunnah yang mulia.",
                    dalil = "إِذَا دَخَلَ أَحَدُكُمُ الْمَسْجِدَ فَلْيُصَلِّ رَكْعَتَيْنِ قَبْلَ أَنْ يَجْلِسَ - HR. Bukhari Muslim"
                ),
                ShalatSunnahEntity(
                    nama = "Istikharah",
                    deskripsi = "Shalat sunnah untuk memohon petunjuk Allah dalam mengambil keputusan penting dalam hidup.",
                    niat = "Ushalli sunnal istikharah rak'ataini lillahi ta'ala",
                    waktu = "Kapan saja ketika membutuhkan petunjuk Allah dalam mengambil keputusan",
                    manfaat = "Mendapat petunjuk dari Allah dalam mengambil keputusan dan ketenangan hati.",
                    dalil = "كَانَ رَسُولُ اللَّهِ يُعَلِّمُنَا الاِسْتِخَارَةَ فِي الأُمُورِ كُلِّهَا - HR. Bukhari"
                ),
                ShalatSunnahEntity(
                    nama = "Taubat",
                    deskripsi = "Shalat sunnah yang dikerjakan setelah melakukan dosa sebagai bentuk penyesalan dan permohonan ampun.",
                    niat = "Ushalli sunnat taubah rak'ataini lillahi ta'ala",
                    waktu = "Setiap kali setelah melakukan dosa atau maksiat",
                    manfaat = "Menghapus dosa, mendapat ampunan Allah, dan membersihkan hati dari kotoran maksiat.",
                    dalil = "مَا مِنْ عَبْدٍ يُذْنِبُ ذَنْبًا فَيُحْسِنُ الطُّهُورَ ثُمَّ يَقُومُ فَيُصَلِّي رَكْعَتَيْنِ - HR. Abu Dawud"
                ),
                ShalatSunnahEntity(
                    nama = "Hajat",
                    deskripsi = "Shalat sunnah yang dikerjakan ketika memiliki hajat atau keperluan yang ingin dipanjatkan kepada Allah.",
                    niat = "Ushalli sunnal hajah rak'ataini lillahi ta'ala",
                    waktu = "Kapan saja ketika memiliki hajat atau keperluan yang mendesak",
                    manfaat = "Memudahkan urusan, dikabulkan hajat, dan mendapat pertolongan Allah SWT.",
                    dalil = "مَنْ تَوَضَّأَ فَأَحْسَنَ الْوُضُوءَ ثُمَّ صَلَّى رَكْعَتَيْنِ يُقْبِلُ عَلَيْهِمَا بِقَلْبِهِ وَوَجْهِهِ - HR. Abu Dawud"
                )
            )
            
            dao.insertAllShalatSunnah(initialData)
        }
    }
}

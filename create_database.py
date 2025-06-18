#!/usr/bin/env python3
"""
Script to create a standalone SQLite database for Shalat Sunnah data.
Run this script to generate jadwal_sholat_database.db file.

Requirements: Python 3 with sqlite3 (built-in)
Usage: python create_database.py
"""

import sqlite3
import os

def create_database():
    # Database file name
    db_file = "jadwal_sholat_database.db"
    
    # Remove existing database if it exists
    if os.path.exists(db_file):
        os.remove(db_file)
        print(f"Removed existing {db_file}")
    
    # Create connection
    conn = sqlite3.connect(db_file)
    cursor = conn.cursor()
    
    # Create table
    cursor.execute('''
        CREATE TABLE shalat_sunnah (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nama TEXT NOT NULL,
            deskripsi TEXT NOT NULL,
            niat TEXT NOT NULL,
            waktu TEXT NOT NULL,
            manfaat TEXT NOT NULL,
            dalil TEXT NOT NULL
        )
    ''')
    
    # Insert data
    sunnah_data = [
        ('Dhuha',
         'Shalat sunnah yang dilakukan setelah matahari terbit hingga sebelum waktu Dzuhur tiba.',
         'Ushalli sunnatad dhuha rak\'ataini lillahi ta\'ala',
         'Setelah matahari terbit hingga sebelum Dzuhur (sekitar pukul 07:00 - 11:00)',
         'Mendatangkan rezeki yang berkah, menambah kekuatan fisik, dan mendapat pahala yang besar dari Allah SWT.',
         'وَالضُّحَى وَاللَّيْلِ إِذَا سَجَى - Demi waktu Dhuha dan malam apabila telah sunyi (QS. Ad-Dhuha: 1-2)'),
        
        ('Rawatib Qabliyah',
         'Shalat sunnah yang dikerjakan sebelum shalat fardhu sebagai persiapan menghadap Allah SWT.',
         'Ushalli sunnar rawatib qabliyah [nama shalat] rak\'ataini lillahi ta\'ala',
         'Sebelum shalat fardhu (Subuh: 2 rakaat, Dzuhur: 4 rakaat, Maghrib: 2 rakaat, Isya: 2 rakaat)',
         'Menyempurnakan shalat fardhu, menambah pahala, dan mempersiapkan hati untuk menghadap Allah.',
         'مَنْ صَلَّى اثْنَتَيْ عَشْرَةَ رَكْعَةً فِي يَوْمٍ وَلَيْلَةٍ بُنِيَ لَهُ بِهِنَّ بَيْتٌ فِي الْجَنَّةِ - HR. Muslim'),

        ('Rawatib Ba\'diyah',
         'Shalat sunnah yang dikerjakan setelah shalat fardhu sebagai penyempurna ibadah.',
         'Ushalli sunnar rawatib ba\'diyah [nama shalat] rak\'ataini lillahi ta\'ala',
         'Setelah shalat fardhu (Dzuhur: 2 rakaat, Maghrib: 2 rakaat, Isya: 2 rakaat)',
         'Menyempurnakan kekurangan dalam shalat fardhu dan menambah pahala di sisi Allah SWT.',
         'مَا مِنْ عَبْدٍ مُسْلِمٍ يُصَلِّي لِلَّهِ كُلَّ يَوْمٍ ثِنْتَيْ عَشْرَةَ رَكْعَةً تَطَوُّعًا - HR. Muslim'),

        ('Tahajjud',
         'Shalat sunnah yang dikerjakan di sepertiga malam terakhir, merupakan waktu yang sangat mulia.',
         'Ushalli sunnat tahajjud rak\'ataini lillahi ta\'ala',
         'Sepertiga malam terakhir hingga sebelum waktu Subuh (sekitar pukul 02:00 - 04:00)',
         'Mendekatkan diri kepada Allah, doa lebih mudah dikabulkan, dan mendapat kedudukan terpuji di akhirat.',
         'وَمِنَ اللَّيْلِ فَتَهَجَّدْ بِهِ نَافِلَةً لَّكَ - Dan pada sebagian malam hari bersembahyang tahajjudlah (QS. Al-Isra: 79)'),
        
        ('Witir',
         'Shalat sunnah muakkad yang dikerjakan dengan jumlah rakaat ganjil sebagai penutup shalat malam.',
         'Ushalli sunnal witri [jumlah rakaat] lillahi ta\'ala',
         'Setelah shalat Isya hingga sebelum Subuh, lebih utama di akhir malam',
         'Menutup ibadah malam dengan sempurna dan mendapat pahala yang besar dari Allah SWT.',
         'إِنَّ اللَّهَ وِتْرٌ يُحِبُّ الْوِتْرَ - Sesungguhnya Allah itu ganjil dan menyukai yang ganjil (HR. Muslim)'),

        ('Tahiyatul Masjid',
         'Shalat sunnah yang dikerjakan ketika memasuki masjid sebagai penghormatan terhadap rumah Allah.',
         'Ushalli sunnat tahiyyatil masjid rak\'ataini lillahi ta\'ala',
         'Setiap kali memasuki masjid, kecuali pada waktu yang dilarang untuk shalat',
         'Menghormati masjid sebagai rumah Allah dan mendapat pahala sunnah yang mulia.',
         'إِذَا دَخَلَ أَحَدُكُمُ الْمَسْجِدَ فَلْيُصَلِّ رَكْعَتَيْنِ قَبْلَ أَنْ يَجْلِسَ - HR. Bukhari Muslim'),

        ('Istikharah',
         'Shalat sunnah untuk memohon petunjuk Allah dalam mengambil keputusan penting dalam hidup.',
         'Ushalli sunnal istikharah rak\'ataini lillahi ta\'ala',
         'Kapan saja ketika membutuhkan petunjuk Allah dalam mengambil keputusan',
         'Mendapat petunjuk dari Allah dalam mengambil keputusan dan ketenangan hati.',
         'كَانَ رَسُولُ اللَّهِ يُعَلِّمُنَا الاِسْتِخَارَةَ فِي الأُمُورِ كُلِّهَا - HR. Bukhari'),

        ('Taubat',
         'Shalat sunnah yang dikerjakan setelah melakukan dosa sebagai bentuk penyesalan dan permohonan ampun.',
         'Ushalli sunnat taubah rak\'ataini lillahi ta\'ala',
         'Setiap kali setelah melakukan dosa atau maksiat',
         'Menghapus dosa, mendapat ampunan Allah, dan membersihkan hati dari kotoran maksiat.',
         'مَا مِنْ عَبْدٍ يُذْنِبُ ذَنْبًا فَيُحْسِنُ الطُّهُورَ ثُمَّ يَقُومُ فَيُصَلِّي رَكْعَتَيْنِ - HR. Abu Dawud'),

        ('Hajat',
         'Shalat sunnah yang dikerjakan ketika memiliki hajat atau keperluan yang ingin dipanjatkan kepada Allah.',
         'Ushalli sunnal hajah rak\'ataini lillahi ta\'ala',
         'Kapan saja ketika memiliki hajat atau keperluan yang mendesak',
         'Memudahkan urusan, dikabulkan hajat, dan mendapat pertolongan Allah SWT.',
         'مَنْ تَوَضَّأَ فَأَحْسَنَ الْوُضُوءَ ثُمَّ صَلَّى رَكْعَتَيْنِ يُقْبِلُ عَلَيْهِمَا بِقَلْبِهِ وَوَجْهِهِ - HR. Abu Dawud')
    ]
    
    cursor.executemany('''
        INSERT INTO shalat_sunnah (nama, deskripsi, niat, waktu, manfaat, dalil)
        VALUES (?, ?, ?, ?, ?, ?)
    ''', sunnah_data)
    
    # Commit changes
    conn.commit()
    
    # Verify data
    cursor.execute('SELECT COUNT(*) FROM shalat_sunnah')
    count = cursor.fetchone()[0]
    print(f"Database created successfully with {count} records")
    
    # Display all records
    cursor.execute('SELECT id, nama FROM shalat_sunnah ORDER BY id')
    records = cursor.fetchall()
    print("\nInserted records:")
    for record in records:
        print(f"  {record[0]}. {record[1]}")
    
    # Close connection
    conn.close()
    
    print(f"\nDatabase file '{db_file}' created successfully!")
    print("You can now interact with this database using any SQLite client.")

if __name__ == "__main__":
    create_database()

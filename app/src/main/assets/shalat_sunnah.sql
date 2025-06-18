-- SQLite database creation script for Shalat Sunnah
-- This file can be used to create and populate the database independently

CREATE TABLE IF NOT EXISTS shalat_sunnah (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nama TEXT NOT NULL,
    deskripsi TEXT NOT NULL,
    niat TEXT NOT NULL,
    waktu TEXT NOT NULL,
    manfaat TEXT NOT NULL,
    dalil TEXT NOT NULL
);

-- Clear existing data
DELETE FROM shalat_sunnah;

-- Insert sunnah prayer data
INSERT INTO shalat_sunnah (nama, deskripsi, niat, waktu, manfaat, dalil) VALUES
('Dhuha', 
 'Shalat sunnah yang dilakukan setelah matahari terbit hingga sebelum waktu Dzuhur tiba.',
 'Ushalli sunnatad dhuha rak''ataini lillahi ta''ala',
 'Setelah matahari terbit hingga sebelum Dzuhur (sekitar pukul 07:00 - 11:00)',
 'Mendatangkan rezeki yang berkah, menambah kekuatan fisik, dan mendapat pahala yang besar dari Allah SWT.'),

('Rawatib Qabliyah',
 'Shalat sunnah yang dikerjakan sebelum shalat fardhu sebagai persiapan menghadap Allah SWT.',
 'Ushalli sunnar rawatib qabliyah [nama shalat] rak''ataini lillahi ta''ala',
 'Sebelum shalat fardhu (Subuh: 2 rakaat, Dzuhur: 4 rakaat, Maghrib: 2 rakaat, Isya: 2 rakaat)',
 'Menyempurnakan shalat fardhu, menambah pahala, dan mempersiapkan hati untuk menghadap Allah.'),

('Rawatib Ba''diyah',
 'Shalat sunnah yang dikerjakan setelah shalat fardhu sebagai penyempurna ibadah.',
 'Ushalli sunnar rawatib ba''diyah [nama shalat] rak''ataini lillahi ta''ala',
 'Setelah shalat fardhu (Dzuhur: 2 rakaat, Maghrib: 2 rakaat, Isya: 2 rakaat)',
 'Menyempurnakan kekurangan dalam shalat fardhu dan menambah pahala di sisi Allah SWT.'),

('Tahajjud',
 'Shalat sunnah yang dikerjakan di sepertiga malam terakhir, merupakan waktu yang sangat mulia.',
 'Ushalli sunnat tahajjud rak''ataini lillahi ta''ala',
 'Sepertiga malam terakhir hingga sebelum waktu Subuh (sekitar pukul 02:00 - 04:00)',
 'Mendekatkan diri kepada Allah, doa lebih mudah dikabulkan, dan mendapat kedudukan terpuji di akhirat.'),

('Witir',
 'Shalat sunnah muakkad yang dikerjakan dengan jumlah rakaat ganjil sebagai penutup shalat malam.',
 'Ushalli sunnal witri [jumlah rakaat] lillahi ta''ala',
 'Setelah shalat Isya hingga sebelum Subuh, lebih utama di akhir malam',
 'Menutup ibadah malam dengan sempurna dan mendapat pahala yang besar dari Allah SWT.'),

('Tahiyatul Masjid',
 'Shalat sunnah yang dikerjakan ketika memasuki masjid sebagai penghormatan terhadap rumah Allah.',
 'Ushalli sunnat tahiyyatil masjid rak''ataini lillahi ta''ala',
 'Setiap kali memasuki masjid, kecuali pada waktu yang dilarang untuk shalat',
 'Menghormati masjid sebagai rumah Allah dan mendapat pahala sunnah yang mulia.'),

('Istikharah',
 'Shalat sunnah untuk memohon petunjuk Allah dalam mengambil keputusan penting dalam hidup.',
 'Ushalli sunnal istikharah rak''ataini lillahi ta''ala',
 'Kapan saja ketika membutuhkan petunjuk Allah dalam mengambil keputusan',
 'Mendapat petunjuk dari Allah dalam mengambil keputusan dan ketenangan hati.'),

('Taubat',
 'Shalat sunnah yang dikerjakan setelah melakukan dosa sebagai bentuk penyesalan dan permohonan ampun.',
 'Ushalli sunnat taubah rak''ataini lillahi ta''ala',
 'Setiap kali setelah melakukan dosa atau maksiat',
 'Menghapus dosa, mendapat ampunan Allah, dan membersihkan hati dari kotoran maksiat.'),

('Hajat',
 'Shalat sunnah yang dikerjakan ketika memiliki hajat atau keperluan yang ingin dipanjatkan kepada Allah.',
 'Ushalli sunnal hajah rak''ataini lillahi ta''ala',
 'Kapan saja ketika memiliki hajat atau keperluan yang mendesak',
 'Memudahkan urusan, dikabulkan hajat, dan mendapat pertolongan Allah SWT.');

-- Verify data insertion
SELECT COUNT(*) as total_records FROM shalat_sunnah;
SELECT * FROM shalat_sunnah ORDER BY id;

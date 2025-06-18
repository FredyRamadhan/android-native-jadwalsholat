package com.kelompok9.jadwalsholat.data.models

/**
 * Simple data class for Shalat Sunnah information
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
 * Hardcoded sunnah prayer data
 * 📝 TO EDIT THIS DATA: Simply modify the values in this list
 */
object ShalatSunnahDataSource {

    val shalatSunnahList = listOf(
        ShalatSunnahData(
            id = 1,
            nama = "Sholat Dhuha",
            deskripsi = "Sholat sunnah yang dikerjakan pada waktu pagi hari setelah matahari terbit hingga menjelang waktu dzuhur.",
            niat = "أُصَلِّى سُنَّةَ الضُّحَى رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ أَدَاءً لِلهِ تَعَالَى (Ushalli sunnatadh dhuhaa rak'ataini mustaqbilal qiblati adaa'an lillaahi ta'aalaa.)",
            waktu = "Dimulai sekitar 15-20 menit setelah matahari terbit (waktu syuruq) hingga 15 menit sebelum masuk waktu sholat dzuhur.",
            manfaat = "Membuka pintu rezeki, sebagai sedekah untuk seluruh persendian tubuh, dan diampuni dosa-dosanya.",
            dalil = "Hadits Riwayat Abu Hurairah: 'Barangsiapa yang menjaga shalat Dhuha, maka dosa-dosa akan diampuni meskipun sebanyak buih di lautan.' (HR. Tirmidzi, Ibnu Majah, dan Ahmad)"
        ),
        ShalatSunnahData(
            id = 2,
            nama = "Sholat Tahajud",
            deskripsi = "Sholat sunnah yang dikerjakan pada malam hari setelah tidur, meskipun hanya tidur sebentar.",
            niat = "أُصَلِّي سُنَّةَ التَّهَجُّدِ رَكْعَتَيْنِ لِلَّهِ تَعَالَى (Ushalli sunnatat tahajjudi rak'ataini lillaahi ta'aalaa.)",
            waktu = "Sepertiga malam terakhir (sekitar pukul 01.00 dini hari hingga menjelang waktu subuh) adalah waktu yang paling utama. Namun, bisa dikerjakan setelah sholat Isya dan tidur.",
            manfaat = "Diangkat derajatnya, dikabulkan doanya, mendapatkan ampunan dari Allah, dan sebagai sarana mendekatkan diri kepada-Nya.",
            dalil = "QS. Al-Isra ayat 79: 'Dan pada sebagian malam, lakukanlah sholat tahajud (sebagai suatu ibadah) tambahan bagimu; mudah-mudahan Tuhanmu mengangkatmu ke tempat yang terpuji.'"
        ),
        ShalatSunnahData(
            id = 3,
            nama = "Sholat Rawatib",
            deskripsi = "Sholat sunnah yang mengiringi sholat fardhu, baik sebelum (qobliyah) maupun sesudah (ba'diyah).",
            niat = "Niat sholat rawatib disesuaikan dengan waktu sholat fardhu yang diiringi. Contoh niat qobliyah subuh: أُصَلِّي سُنَّةَ الصُّبْحِ رَكْعَتَيْنِ قَبْلِيَّةً لِلهِ تَعَالَى (Ushalli sunnatas subhi rak'ataini qabliyyatan lillaahi ta'aalaa.)",
            waktu = "Sebelum atau sesudah sholat fardhu lima waktu.",
            manfaat = "Menyempurnakan pahala sholat fardhu, dibangunkan rumah di surga, dan menghapus dosa.",
            dalil = "Hadits Riwayat Muslim: 'Tidaklah seorang hamba muslim mengerjakan shalat sunnah karena Allah setiap hari dua belas rakaat, melainkan Allah akan membangunkan untuknya sebuah rumah di surga.' (HR. Muslim)"
        ),
        ShalatSunnahData(
            id = 4,
            nama = "Sholat Istikharah",
            deskripsi = "Sholat sunnah yang dikerjakan untuk memohon petunjuk kepada Allah dalam menentukan pilihan di antara beberapa pilihan yang sulit.",
            niat = "أُصَلِّي سُنَّةَ الْاِسْتِخَارَةِ رَكْعَتَيْنِ لِلَّهِ تَعَالَى (Ushalli sunnatal istikhaarati rak'ataini lillaahi ta'aalaa.)",
            waktu = "Dapat dikerjakan kapan saja di luar waktu-waktu yang diharamkan untuk sholat. Waktu terbaik adalah pada sepertiga malam terakhir.",
            manfaat = "Mendapatkan petunjuk dan kemantapan hati dalam mengambil keputusan, serta menyerahkan segala urusan kepada Allah.",
            dalil = "Hadits Riwayat Jabir bin Abdullah: 'Rasulullah SAW mengajari kami shalat istikharah dalam setiap urusan, sebagaimana beliau mengajari kami surat dari Al-Qur'an.' (HR. Bukhari)"
        ),
        ShalatSunnahData(
            id = 5,
            nama = "Sholat Hajat",
            deskripsi = "Sholat sunnah yang dikerjakan ketika seseorang memiliki suatu keinginan atau hajat dan memohon kepada Allah agar hajatnya dikabulkan.",
            niat = "أُصَلِّي سُنَّةَ الْحَاجَةِ رَكْعَتَيْنِ لِلَّهِ تَعَالَى (Ushalli sunnatal haajati rak'ataini lillaahi ta'aalaa.)",
            waktu = "Dapat dilaksanakan kapan saja, baik siang maupun malam, di luar waktu yang dilarang untuk sholat. Waktu yang mustajab adalah pada sepertiga malam terakhir.",
            manfaat = "Sebagai sarana untuk memohon kepada Allah agar keinginan atau hajatnya terpenuhi.",
            dalil = "Hadits Riwayat Tirmidzi dan Ibnu Majah: 'Barangsiapa mempunyai hajat kepada Allah atau kepada salah seorang dari anak Adam, maka hendaklah ia berwudhu dan menyempurnakannya, kemudian shalat dua rakaat...'"
        ),
        ShalatSunnahData(
            id = 6,
            nama = "Sholat Taubat",
            deskripsi = "Sholat sunnah yang dilakukan untuk memohon ampunan kepada Allah atas segala dosa dan kesalahan yang telah diperbuat.",
            niat = "أُصَلِّي سُنَّةَ التَّوْبَةِ رَكْعَتَيْنِ لِلَّهِ تَعَالَى (Ushalli sunnatat taubati rak'ataini lillaahi ta'aalaa.)",
            waktu = "Bisa dilakukan kapan saja, namun waktu yang paling utama adalah di sepertiga malam terakhir. Sebaiknya dilakukan sesegera mungkin setelah menyadari telah berbuat dosa.",
            manfaat = "Mendapatkan ampunan dari Allah, membersihkan diri dari dosa, dan memberikan ketenangan batin.",
            dalil = "Hadits Riwayat Abu Bakar Ash-Shiddiq: 'Tidaklah seorang hamba melakukan dosa kemudian ia bersuci dengan baik, lalu berdiri untuk shalat dua rakaat, kemudian ia meminta ampunan kepada Allah, melainkan Allah akan mengampuninya.' (HR. Abu Dawud, Tirmidzi, dan Ibnu Majah)"
        ),
        ShalatSunnahData(
            id = 7,
            nama = "Sholat Witir",
            deskripsi = "Sholat sunnah mu'akkad (sangat dianjurkan) yang dikerjakan pada malam hari dengan jumlah rakaat ganjil sebagai penutup sholat malam.",
            niat = "Niat disesuaikan dengan jumlah rakaat. Contoh niat 3 rakaat: أُصَلِّي سُنَّةَ الْوِتْرِ ثَلَاثَ رَكَعَاتٍ مُسْتَقْبِلَ الْقِبْلَةِ أَدَاءً لِلهِ تَعَالَى (Ushalli sunnatal witri tsalaatsa raka'aatin mustaqbilal qiblati adaa'an lillaahi ta'aalaa.)",
            waktu = "Setelah sholat Isya hingga terbit fajar (masuk waktu subuh). Merupakan sholat penutup dari rangkaian ibadah sholat malam.",
            manfaat = "Dicintai oleh Allah, sebagai penyempurna sholat malam, dan mendapatkan keutamaan yang besar.",
            dalil = "Hadits Riwayat Ali bin Abi Thalib: 'Sesungguhnya Allah itu Witir (Ganjil) dan menyukai yang ganjil, maka shalat witirlah wahai ahli Al-Qur'an.' (HR. Abu Dawud dan Tirmidzi)"
        ),
        ShalatSunnahData(
            id = 8,
            nama = "Sholat Tarawih",
            deskripsi = "Sholat sunnah yang khusus dikerjakan pada malam-malam bulan Ramadhan.",
            niat = "أُصَلِّي سُنَّةَ التَّرَاوِيْحِ رَكْعَتَيْنِ (مَأْمُوْمًا/إِمَامًا) لِلهِ تَعَالَى (Ushalli sunnatat taraawiihi rak'ataini (ma'muman/imaman) lillaahi ta'aalaa.)",
            waktu = "Setelah sholat Isya hingga terbit fajar selama bulan Ramadhan.",
            manfaat = "Diampuni dosa-dosa yang telah lalu, mendapatkan pahala seperti sholat semalam suntuk jika dikerjakan berjamaah bersama imam sampai selesai, dan menghidupkan malam Ramadhan.",
            dalil = "Hadits Riwayat Bukhari dan Muslim: 'Barangsiapa yang menunaikan shalat malam di bulan Ramadhan (shalat tarawih) dengan keimanan dan mengharap pahala dari Allah, maka diampuni dosa-dosanya yang telah lalu.'"
        ),
        ShalatSunnahData(
            id = 9,
            nama = "Sholat Idul Fitri",
            deskripsi = "Sholat sunnah mu'akkad yang dilaksanakan pada tanggal 1 Syawal sebagai tanda berakhirnya bulan Ramadhan dan hari kemenangan.",
            niat = "أُصَلِّي سُنَّةً لِعِيْدِ الْفِطْرِ رَكْعَتَيْنِ (مَأْمُوْمًا/إِمَامًا) لِلهِ تَعَالَى (Ushalli sunnatan li 'iidil fitri rak'ataini (ma'muman/imaman) lillaahi ta'aalaa.)",
            waktu = "Dimulai sejak matahari terbit setinggi tombak hingga waktu zawal (tergelincirnya matahari).",
            manfaat = "Menampakkan syiar Islam, sebagai bentuk rasa syukur kepada Allah atas nikmat Ramadhan, dan menyempurnakan ibadah puasa.",
            dalil = "Perbuatan (fi'il) Nabi Muhammad SAW yang senantiasa melaksanakannya dan memerintahkan para sahabat, baik laki-laki maupun perempuan, untuk keluar menuju lapangan untuk melaksanakannya."
        ),
        ShalatSunnahData(
            id = 10,
            nama = "Sholat Idul Adha",
            deskripsi = "Sholat sunnah mu'akkad yang dilaksanakan pada tanggal 10 Dzulhijjah, bertepatan dengan hari raya kurban.",
            niat = "أُصَلِّي سُنَّةً لِعِيْدِ الْأَضْحَى رَكْعَتَيْنِ (مَأْمُوْمًا/إِمَامًا) لِلهِ تَعَالَى (Ushalli sunnatan li 'iidil adhaa rak'ataini (ma'muman/imaman) lillaahi ta'aalaa.)",
            waktu = "Dimulai sejak matahari terbit setinggi tombak hingga waktu zawal (tergelincirnya matahari). Disunnahkan untuk menyegerakan pelaksanaannya.",
            manfaat = "Meneladani Nabi Ibrahim AS, mengagungkan syiar Allah, dan menjadi bagian dari rangkaian ibadah haji bagi yang tidak melaksanakannya.",
            dalil = "QS. Al-Kautsar ayat 2: 'Maka laksanakanlah shalat karena Tuhanmu, dan berkurbanlah.' Para ulama menafsirkan sholat di sini adalah Sholat Idul Adha."
        ),
        ShalatSunnahData(
            id = 11,
            nama = "Sholat Tahiyatul Masjid",
            deskripsi = "Sholat sunnah dua rakaat yang dikerjakan sebagai bentuk penghormatan kepada masjid. Dilakukan setiap kali memasuki masjid dan sebelum duduk.",
            niat = "أُصَلِّي سُنَّةَ تَحِيَّةِ الْمَسْجِدِ رَكْعَتَيْنِ لِلهِ تَعَالَى (Ushalli sunnata tahiyyatil masjidi rak'ataini lillaahi ta'aalaa.)",
            waktu = "Setiap saat ketika memasuki masjid, sebelum duduk, selama bukan waktu yang diharamkan untuk sholat.",
            manfaat = "Memuliakan rumah Allah (masjid), mendapatkan pahala, dan menyempurnakan adab ketika masuk masjid.",
            dalil = "Hadits Riwayat Abu Qatadah: 'Jika salah seorang dari kalian masuk masjid, maka janganlah ia duduk sehingga ia melaksanakan shalat dua rakaat.' (HR. Bukhari dan Muslim)"
        ),
        ShalatSunnahData(
            id = 12,
            nama = "Sholat Tasbih",
            deskripsi = "Sholat sunnah yang di dalamnya dibaca kalimat tasbih (Subhanallah walhamdulillah wala ilaha illallah wallahu akbar) sebanyak 300 kali dalam empat rakaat.",
            niat = "أُصَلِّي سُنَّةَ التَّسْبِيْحِ رَكْعَتَيْنِ (أَوْ أَرْبَعَ رَكَعَاتٍ) لِلهِ تَعَالَى (Ushalli sunnatat tasbiihi rak'ataini (atau arba'a raka'aatin) lillaahi ta'aalaa.)",
            waktu = "Dapat dikerjakan kapan saja, siang atau malam, di luar waktu yang dilarang untuk sholat. Jika dikerjakan malam hari, lebih utama dengan dua kali salam.",
            manfaat = "Diampuni dosa-dosanya, memberatkan timbangan amal baik, dan merupakan salah satu cara untuk banyak berdzikir kepada Allah.",
            dalil = "Hadits yang diriwayatkan dari Ibnu Abbas, bahwa Rasulullah SAW mengajarkan sholat ini kepada pamannya, Abbas bin Abdul Muthalib, dan bersabda, 'Jika engkau mampu, kerjakanlah setiap hari... jika tidak, maka setiap Jumat... jika tidak, maka setiap bulan... jika tidak, maka setiap tahun... jika tidak, maka sekali seumur hidup.' (HR. Abu Dawud dan Tirmidzi, hadits ini memiliki beberapa perdebatan di kalangan ulama mengenai tingkat kesahihannya, namun banyak yang memperbolehkannya sebagai fadhailul a'mal atau amalan keutamaan)."
        ),
        ShalatSunnahData(
            id = 13,
            nama = "Sholat Isyraq (Syuruq)",
            deskripsi = "Sholat sunnah dua rakaat yang dikerjakan setelah matahari terbit sepenuhnya, kira-kira setinggi satu tombak.",
            niat = "أُصَلِّيْ سُنَّةَ الإِشْرَاقِ رَكْعَتَيْنِ لِلّٰهِ تَعَالَى (Ushalli sunnatal isyraqi rak'ataini lillahi ta'aalaa.)",
            waktu = "Sekitar 15-20 menit setelah matahari terbit.",
            manfaat = "Mendapatkan pahala seperti haji dan umrah yang sempurna bagi yang melaksanakannya setelah sholat subuh berjamaah dan berdzikir hingga matahari terbit.",
            dalil = "Hadits Riwayat Anas bin Malik: Rasulullah SAW bersabda, 'Barangsiapa yang shalat subuh berjamaah, kemudian ia duduk berdzikir kepada Allah hingga matahari terbit, lalu ia shalat dua rakaat, maka baginya pahala seperti pahala haji dan umrah, sempurna, sempurna, sempurna.' (HR. Tirmidzi, hadits ini berstatus hasan)."
        ),
        ShalatSunnahData(
            id = 14,
            nama = "Sholat Sunnah Wudhu",
            deskripsi = "Sholat sunnah dua rakaat yang dikerjakan setiap kali selesai berwudhu.",
            niat = "أُصَلِّي سُنَّةَ الْوُضُوْءِ رَكْعَتَيْنِ لِلهِ تَعَالَى (Ushalli sunnatal wudhu'i rak'ataini lillaahi ta'aalaa.)",
            waktu = "Segera setelah selesai berwudhu, di luar waktu-waktu yang diharamkan untuk sholat.",
            manfaat = "Menjadi penyebab masuk surga, menghapus dosa-dosa, dan merupakan bentuk syukur atas nikmat bersuci.",
            dalil = "Hadits Riwayat Abu Hurairah, bahwa Nabi Muhammad SAW berkata kepada Bilal bin Rabah setelah sholat fajar, 'Wahai Bilal, ceritakan kepadaku amal paling utama yang kamu kerjakan dalam Islam, karena aku mendengar suara terompahmu di surga.' Bilal menjawab, 'Tidak ada amalan yang lebih aku harapkan daripada setiap kali aku bersuci (berwudhu) pada waktu malam atau siang, aku selalu shalat dengan wudhu itu sebanyak yang Allah takdirkan untukku.' (HR. Bukhari dan Muslim)"
        ),
        ShalatSunnahData(
            id = 15,
            nama = "Sholat Awwabin",
            deskripsi = "Sholat sunnah yang dikerjakan di antara waktu sholat Maghrib dan Isya.",
            niat = "أُصَلِّى سُنَّةَ الأَوَّابِينَ رَكْعَتَيْنِ لِلهِ تَعَالَى (Ushalli sunnatal awwabina rak'ataini lillahi ta'ala.)",
            waktu = "Setelah sholat Maghrib dan sebelum masuk waktu sholat Isya.",
            manfaat = "Pahalanya disebut sebanding dengan ibadah dua belas tahun, dan sebagai penebus kelalaian di waktu siang hari.",
            dalil = "Hadits Riwayat Abu Hurairah: Rasulullah SAW bersabda, 'Barang siapa mengerjakan sholat sunnah enam rakaat setelah sholat Maghrib dan di antara sholat-sholat itu tidak berkata dengan kata-kata yang buruk, maka sholatnya sebanding ibadah dua belas tahun.' (HR. Tirmidzi dan Ibnu Majah). Sebagian ulama menganggap hadits ini dhaif (lemah) namun tetap dianjurkan sebagai amalan keutamaan (fadhailul a'mal)."
        ),
        ShalatSunnahData(
            id = 16,
            nama = "Sholat Gerhana (Kusuf & Khusuf)",
            deskripsi = "Sholat sunnah mu'akkad yang dikerjakan ketika terjadi gerhana matahari (kusuf) atau gerhana bulan (khusuf). Dilakukan sebanyak dua rakaat dengan tata cara khusus, yaitu terdapat dua kali berdiri, dua kali membaca surat, dua kali ruku', dan dua kali sujud di setiap rakaatnya.",
            niat = "Niat untuk Gerhana Matahari (Kusuf): أُصَلِّيْ سُنَّةً لِكُسُوْفِ الشَّمْسِ رَكْعَتَيْنِ (مَأْمُوْمًا/إِمَامًا) لِلّٰهِ تَعَالَى (Ushalli sunnatan likusuufis syamsi rak'ataini (ma'muman/imaman) lillahi ta'aalaa.)\nNiat untuk Gerhana Bulan (Khusuf): أُصَلِّيْ سُنَّةً لِخُسُوْفِ الْقَمَرِ رَكْعَتَيْنِ (مَأْمُوْمًا/إِمَامًا) لِلّٰهِ تَعَالَى (Ushalli sunnatan likhusuufil qamari rak'ataini (ma'muman/imaman) lillahi ta'aalaa.)",
            waktu = "Sejak dimulainya peristiwa gerhana hingga gerhana tersebut berakhir (bulan atau matahari kembali tampak utuh).",
            manfaat = "Sebagai pengingat akan kebesaran dan kekuasaan Allah SWT, mengajak umat untuk bertaubat, bersedekah, berdzikir, dan memohon ampunan.",
            dalil = "Hadits Riwayat Aisyah RA: 'Sesungguhnya matahari dan bulan adalah dua tanda di antara tanda-tanda kebesaran Allah. Keduanya tidak mengalami gerhana karena kematian atau kelahiran seseorang. Jika kalian melihat gerhana, maka berdoalah kepada Allah, bertakbirlah, laksanakan shalat, dan bersedekahlah.' (HR. Bukhari dan Muslim)"
        ),
        ShalatSunnahData(
            id = 17,
            nama = "Sholat Istisqa",
            deskripsi = "Sholat sunnah yang dilakukan secara berjamaah di tanah lapang untuk memohon kepada Allah agar diturunkan hujan ketika terjadi kekeringan yang panjang.",
            niat = "أُصَلِّيْ سُنَّةَ الْإِسْتِسْقَاءِ رَكْعَتَيْنِ (مَأْمُوْمًا/إِمَامًا) لِلّٰهِ تَعَالَى (Ushalli sunnatal istisqa'i rak'ataini (ma'muman/imaman) lillahi ta'aalaa.)",
            waktu = "Dilaksanakan pada waktu dhuha ketika musim kemarau panjang telah menyebabkan kesulitan. Waktu pastinya ditentukan oleh pemimpin atau pemerintah setempat setelah mengimbau masyarakat untuk bertaubat dan berpuasa.",
            manfaat = "Menunjukkan kerendahan diri di hadapan Allah, memohon rahmat-Nya berupa hujan, dan sebagai sarana introspeksi serta taubat kolektif.",
            dalil = "Hadits Riwayat Abdullah bin Zaid: 'Nabi Muhammad SAW keluar menuju tanah lapang untuk melaksanakan shalat Istisqa. Beliau menghadap kiblat, membalik posisi selendangnya, kemudian shalat dua rakaat.' (HR. Bukhari dan Muslim). Dalam sholat ini juga dianjurkan ada khutbah sebelum atau sesudah sholat untuk mengajak umat bertaubat."
        )
    )

    /**
     * Get sunnah prayer by ID
     */
    fun getShalatSunnahById(id: Int): ShalatSunnahData? {
        return shalatSunnahList.find { it.id == id }
    }
}
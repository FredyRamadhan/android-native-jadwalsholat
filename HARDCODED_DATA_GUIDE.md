# 📝 Hardcoded Data Management Guide

## 🎯 **Simple & Clean Architecture**

Your app now uses **hardcoded data** - no databases, no external files, just simple Kotlin data structures that are easy to edit and maintain!

## 📊 **Where to Find & Edit Data**

### **🕌 Sunnah Prayer Data**
**File**: `app/src/main/java/com/kelompok9/jadwalsholat/data/models/ShalatSunnahData.kt`

```kotlin
object ShalatSunnahDataSource {
    val shalatSunnahList = listOf(
        ShalatSunnahData(
            id = 1,
            nama = "Dhuha",
            deskripsi = "Shalat sunnah yang dilakukan setelah matahari terbit...",
            niat = "Ushalli sunnatad dhuha rak'ataini lillahi ta'ala",
            waktu = "Setelah matahari terbit hingga sebelum Dzuhur...",
            manfaat = "Mendatangkan rezeki yang berkah...",
            dalil = "وَالضُّحَى وَاللَّيْلِ إِذَا سَجَى - Demi waktu Dhuha..."
        ),
        // ... 8 more entries
    )
}
```

### **🕐 Prayer Times Data**
**Source**: Real-time from MyQuran API
**Display**: Enhanced to show ALL prayer times including:
- **Imsak** (الإمساك) - Pre-dawn
- **Subuh** (الفجر) - Dawn
- **Terbit** (الشروق) - Sunrise  
- **Dhuha** (الضحى) - Mid-morning
- **Dzuhur** (الظهر) - Noon
- **Ashar** (العصر) - Afternoon
- **Maghrib** (المغرب) - Sunset
- **Isya** (العشاء) - Night

## ✏️ **How to Edit Sunnah Prayer Data**

### **Step 1: Open the Data File**
Navigate to: `app/src/main/java/com/kelompok9/jadwalsholat/data/models/ShalatSunnahData.kt`

### **Step 2: Edit Any Field**
```kotlin
ShalatSunnahData(
    id = 1,                    // ✏️ Unique number (don't change)
    nama = "Your Prayer Name", // ✏️ Prayer name in Indonesian
    deskripsi = "Your description here...", // ✏️ Detailed description
    niat = "Arabic intention...", // ✏️ Prayer intention in Arabic
    waktu = "When to perform...", // ✏️ Timing guidelines
    manfaat = "Benefits...", // ✏️ Spiritual benefits
    dalil = "Quranic verse or Hadith..." // ✏️ Islamic evidence
),
```

### **Step 3: Add New Prayer**
```kotlin
// Add to the end of the list
ShalatSunnahData(
    id = 10, // Next available ID
    nama = "New Prayer Name",
    deskripsi = "Description of the new prayer...",
    niat = "Arabic intention...",
    waktu = "When to perform this prayer...",
    manfaat = "Benefits of this prayer...",
    dalil = "Islamic evidence..."
),
```

### **Step 4: Remove Prayer**
Simply delete the entire `ShalatSunnahData(...)` block

### **Step 5: Compile & Run**
- Build the app
- Changes appear immediately
- No database setup needed!

## 🏗️ **Current Data Structure**

### **📋 Complete Sunnah Prayer List:**

1. **Dhuha** - Morning blessing prayer
2. **Rawatib Qabliyah** - Before obligatory prayers
3. **Rawatib Ba'diyah** - After obligatory prayers  
4. **Tahajjud** - Night prayer (last third of night)
5. **Witir** - Odd-numbered closing prayer
6. **Tahiyatul Masjid** - Mosque greeting prayer
7. **Istikharah** - Guidance-seeking prayer
8. **Taubat** - Repentance prayer
9. **Hajat** - Need-fulfillment prayer

### **📱 Enhanced Prayer Times Display:**

**Before (5 prayers):**
- Subuh, Dzuhur, Ashar, Maghrib, Isya

**Now (8 times):**
- Imsak, Subuh, Terbit, Dhuha, Dzuhur, Ashar, Maghrib, Isya

## 🎨 **Data Fields Explained**

### **For Sunnah Prayers:**
- **`id`**: Unique identifier (used for navigation)
- **`nama`**: Prayer name displayed in list
- **`deskripsi`**: Brief description shown in list
- **`niat`**: Arabic intention shown in detail screen
- **`waktu`**: Timing guidelines with specific hours
- **`manfaat`**: Spiritual benefits and rewards
- **`dalil`**: Quranic verses or Hadith references

### **For Prayer Times:**
- **`name`**: Prayer name (Indonesian)
- **`time`**: Exact time from API (e.g., "04:22")
- **`arabicName`**: Arabic name for display

## 🔧 **Technical Implementation**

### **Data Flow:**
```
Hardcoded Data (ShalatSunnahDataSource)
         ↓
Direct Access (No Repository/Database)
         ↓
UI Components (Immediate Display)
```

### **Key Files:**
- **Data**: `ShalatSunnahData.kt` - All sunnah prayer information
- **List Screen**: `DaftarShalatSunnahScreen.kt` - Shows prayer list
- **Detail Screen**: `ShalatSunnahDetailScreen.kt` - Shows full details
- **Prayer Times**: `DaftarHariIni.kt` - Shows all 8 prayer times

## ✅ **Benefits of Hardcoded Approach**

### **🚀 Simplicity:**
- No database setup
- No external files
- No migration scripts
- Direct code editing

### **⚡ Performance:**
- Instant data access
- No database queries
- Faster app startup
- Reduced memory usage

### **🛠️ Maintenance:**
- Easy to edit in IDE
- Version control friendly
- No data corruption risk
- Simple backup (just code)

### **🔍 Debugging:**
- Data visible in code
- Easy to trace issues
- No database tools needed
- Clear data structure

## 📝 **Example: Adding a New Prayer**

```kotlin
// Add this to ShalatSunnahDataSource.shalatSunnahList
ShalatSunnahData(
    id = 10,
    nama = "Tarawih",
    deskripsi = "Shalat sunnah yang dilakukan pada malam bulan Ramadan setelah shalat Isya.",
    niat = "Ushalli sunnat tarawih rak'ataini lillahi ta'ala",
    waktu = "Setelah shalat Isya hingga sebelum Witir selama bulan Ramadan",
    manfaat = "Mendapat pahala berlipat ganda di bulan suci, mendekatkan diri kepada Allah.",
    dalil = "مَنْ قَامَ رَمَضَانَ إِيمَانًا وَاحْتِسَابًا غُفِرَ لَهُ مَا تَقَدَّمَ مِنْ ذَنْبِهِ - HR. Bukhari"
)
```

## 🎯 **Quick Edit Checklist**

- [ ] Open `ShalatSunnahData.kt`
- [ ] Find the prayer you want to edit
- [ ] Modify the text in quotes
- [ ] Save the file
- [ ] Build and run the app
- [ ] See changes immediately!

**No databases, no external files, no complexity - just simple, clean, maintainable code!** ✨

# 📊 Database Management Guide - Jadwal Sholat App

## 🔍 **How Data Management Works**

### **Current Data Flow Architecture:**

```
External Database File (.db)
         ↓
    [Copy Process]
         ↓
Internal App Database (Room)
         ↓
    Repository Layer
         ↓
    ViewModel (StateFlow)
         ↓
    UI Components
```

## 📁 **Database Storage Locations**

### **1. External Database File**
- **Location**: `jadwal_sholat_database.db` (project root)
- **Purpose**: Standalone database for manual editing
- **Access**: Direct SQLite client access
- **Usage**: Independent data management

### **2. Internal App Database**
- **Location**: `/data/data/com.kelompok9.jadwalsholat/databases/`
- **Purpose**: Runtime app database
- **Access**: Through Room ORM
- **Usage**: App reads from this database

## 🔄 **Data Synchronization Process**

### **How External → Internal Works:**

1. **App Startup**: `AppDatabase.getDatabase()` is called
2. **Copy Check**: `copyDatabaseFromExternalFile()` runs
3. **File Detection**: Checks if external `.db` file exists
4. **Copy Operation**: If external exists and internal doesn't, copies data
5. **Room Initialization**: Room database starts with copied data
6. **Fallback**: If no external file, uses hardcoded data

### **Code Implementation:**
```kotlin
private fun copyDatabaseFromExternalFile(context: Context) {
    val externalDbFile = File(context.getExternalFilesDir(null), "jadwal_sholat_database.db")
    val internalDbFile = File(context.getDatabasePath("jadwal_sholat_database").absolutePath)
    
    if (externalDbFile.exists() && !internalDbFile.exists()) {
        // Copy external to internal
        externalDbFile.inputStream().use { input ->
            FileOutputStream(internalDbFile).use { output ->
                input.copyTo(output)
            }
        }
    }
}
```

## 🗄️ **Database Schema (Updated)**

### **Table: shalat_sunnah**
```sql
CREATE TABLE shalat_sunnah (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nama TEXT NOT NULL,           -- Prayer name
    deskripsi TEXT NOT NULL,      -- Description
    niat TEXT NOT NULL,           -- Intention (Arabic)
    waktu TEXT NOT NULL,          -- Timing
    manfaat TEXT NOT NULL,        -- Benefits
    dalil TEXT NOT NULL           -- 🆕 Islamic evidence/reference
);
```

### **Migration Support:**
- **Version 1 → 2**: Adds `dalil` column
- **Auto-migration**: Handled by Room
- **Backward compatibility**: Maintained

## 🛠️ **How to Manage Data**

### **Option 1: Use External Database File**
1. **Edit**: Open `jadwal_sholat_database.db` with SQLite browser
2. **Modify**: Add/edit/delete records
3. **Deploy**: Copy to app's external files directory
4. **Restart**: App will use your data on next launch

### **Option 2: Regenerate Database**
```bash
python create_database.py
```
- Creates fresh `jadwal_sholat_database.db`
- Includes all 9 sunnah prayers with dalil
- Ready for immediate use

### **Option 3: SQL Script**
```bash
sqlite3 new_database.db < app/src/main/assets/shalat_sunnah.sql
```
- Creates database from SQL script
- Customizable for different data sets

## 📱 **App Data Access Pattern**

### **Repository Layer:**
```kotlin
class ShalatSunnahRepository(context: Context) {
    private val dao: ShalatSunnahDao = AppDatabase.getDatabase(context).shalatSunnahDao()
    
    fun getAllShalatSunnah(): Flow<List<ShalatSunnahData>> {
        return dao.getAllShalatSunnah().map { entities ->
            entities.map { it.toShalatSunnahData() }
        }
    }
}
```

### **ViewModel Layer:**
```kotlin
class ShalatSunnahViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShalatSunnahRepository(application)
    
    private val _shalatSunnahList = MutableStateFlow<List<ShalatSunnahData>>(emptyList())
    val shalatSunnahList: StateFlow<List<ShalatSunnahData>> = _shalatSunnahList.asStateFlow()
}
```

### **UI Layer:**
```kotlin
@Composable
fun DaftarShalatSunnahScreen(viewModel: ShalatSunnahViewModel = viewModel()) {
    val shalatSunnahList by viewModel.shalatSunnahList.collectAsState()
    // UI renders data reactively
}
```

## 🔧 **Development Workflow**

### **For Adding New Data:**
1. **Edit External DB**: Use SQLite browser to modify `jadwal_sholat_database.db`
2. **Test Changes**: Place file in app's external directory
3. **Verify**: Run app to see changes
4. **Update Code**: If schema changes, update entities and migrations

### **For Schema Changes:**
1. **Update Entity**: Modify `ShalatSunnahEntity.kt`
2. **Create Migration**: Add migration in `AppDatabase.kt`
3. **Increment Version**: Update database version number
4. **Update External Files**: Regenerate `.db` and `.sql` files

## 🎯 **Key Benefits of This Architecture**

### **✅ Flexibility:**
- Edit data without recompiling app
- Use external database tools
- Independent data management

### **✅ Performance:**
- Fast internal database access
- Room ORM optimizations
- Reactive UI updates

### **✅ Reliability:**
- Automatic fallback to hardcoded data
- Migration support for schema changes
- Error handling for file operations

### **✅ Development:**
- Easy testing with different datasets
- Version control for database changes
- Separation of data and code

## 🚨 **Important Notes**

### **Data Priority:**
1. **External Database** (if exists)
2. **Hardcoded Data** (fallback)

### **Copy Timing:**
- **Only on first run** (when internal DB doesn't exist)
- **Manual deletion** of internal DB forces re-copy
- **App updates** may require database reset

### **File Locations:**
- **External**: `<app_external_files>/jadwal_sholat_database.db`
- **Internal**: `/data/data/com.kelompok9.jadwalsholat/databases/jadwal_sholat_database`

## 📋 **Updated Features**

### **🆕 Dalil Column Added:**
- **Purpose**: Islamic evidence/references for each prayer
- **Content**: Quranic verses and Hadith references
- **Display**: Shows in detail screen with book icon
- **Format**: Arabic text with translation

### **🔄 Migration Support:**
- **Version 2**: Includes dalil column
- **Automatic**: Room handles migration seamlessly
- **Backward Compatible**: Works with old and new schemas

This architecture provides maximum flexibility for data management while maintaining app performance and reliability! 🚀

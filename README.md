# Jadwal Sholat App

A modern Android application for displaying Islamic prayer times with real-time data from MyQuran API.

## Features

### ✅ Implemented Features

1. **Real-time Prayer Times**
   - Fetches live prayer times from MyQuran API (https://api.myquran.com/)
   - Displays 5 daily prayers: Subuh, Dzuhur, Ashar, Maghrib, Isya
   - Shows prayer times with modern, beautiful UI design

2. **Location Settings**
   - Choose from 500+ Indonesian cities
   - Search functionality for easy city selection
   - **Fixed**: Proper location permission handling with user prompts
   - **Fixed**: Accurate device location detection using GPS
   - Persistent location storage using DataStore

3. **Sunnah Prayer Database**
   - **New**: SQLite database with Room for sunnah prayer data
   - **New**: Comprehensive sunnah prayer information (nama, deskripsi, niat, waktu, manfaat)
   - **New**: Click-to-detail navigation for each sunnah prayer
   - **New**: Detailed screens showing complete prayer information
   - Pre-populated with 9 common sunnah prayers

4. **Modern UI/UX**
   - Material Design 3 components
   - Loading states with progress indicators
   - Error handling with retry functionality
   - Enhanced card layouts with icons
   - **New**: Minimal list view with click-to-detail pattern
   - **New**: Comprehensive detail screens for sunnah prayers
   - Responsive design

5. **Data Management**
   - Repository pattern for clean architecture
   - Retrofit for API communication
   - **New**: Room database for local sunnah prayer data
   - DataStore for location preferences
   - StateFlow for reactive UI updates

## Technical Implementation

### Architecture
- **MVVM Pattern**: ViewModel + Repository + API Service
- **Reactive Programming**: StateFlow and Compose State
- **Dependency Management**: Retrofit, Gson, DataStore

### Key Components

#### API Integration
- `MyQuranApiService`: Retrofit interface for API calls
- `PrayerTimesRepository`: Data layer handling API and local storage
- Real-time data fetching with error handling

#### UI Components
- `DaftarHariIniScreen`: Enhanced prayer times display
- `LocationSettingsScreen`: City selection with search
- `WaktuShalatItem`: Modern prayer time cards
- Loading, success, and error states

#### Data Models
- `PrayerTimesResponse`: API response models
- `City`: City information from API
- `LocationInfo`: User's selected location
- State classes for UI management

### Dependencies Added
```kotlin
// Network & API
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

// Location Services
implementation("com.google.android.gms:play-services-location:21.0.1")
implementation("com.google.accompanist:accompanist-permissions:0.32.0")

// Database
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")

// Data Storage
implementation("androidx.datastore:datastore-preferences:1.0.0")

// ViewModel & Compose
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
```

## Usage

### Setting Location
1. Open the app and tap "Pengaturan Lokasi" from the main menu
2. Choose from:
   - **Search Cities**: Type to find your city from 500+ Indonesian cities
   - **Use Current Location**: Automatically detect nearest city (requires location permission)
3. Selected location is saved and persists across app sessions

### Viewing Prayer Times
1. Tap "Jadwal Sholat Hari Ini" from the main menu
2. View today's prayer times for your selected location
3. Pull to refresh or tap the refresh icon to update times
4. Times are displayed with:
   - Prayer name in Indonesian
   - Exact time in local format
   - Appropriate icons for each prayer
   - Location and date information

### Exploring Sunnah Prayers
1. Tap "Daftar Shalat Sunnah" from the main menu
2. Browse through 9 comprehensive sunnah prayers
3. Each item shows:
   - Prayer name
   - Brief description (truncated)
   - Recommended time
4. Tap any item to view detailed information including:
   - Complete description
   - Prayer intention (niat) in Arabic
   - Detailed timing guidelines
   - Spiritual benefits and rewards

## API Integration

### MyQuran API Endpoints Used
- `GET /v2/sholat/kota/semua` - Get all Indonesian cities
- `GET /v2/sholat/jadwal/{cityId}/{year}/{month}/{day}` - Get prayer times

### Sample API Response
```json
{
  "status": true,
  "data": {
    "lokasi": "KOTA JAKARTA",
    "jadwal": {
      "tanggal": "Rabu, 18/12/2024",
      "subuh": "04:12",
      "dzuhur": "11:53",
      "ashar": "15:19",
      "maghrib": "18:07",
      "isya": "19:23"
    }
  }
}
```

## Error Handling

- **Network Errors**: Graceful error messages with retry options
- **Location Errors**: Fallback to Jakarta if location detection fails
- **API Errors**: User-friendly error messages
- **Permission Errors**: Fallback behavior when location permission denied

## Future Enhancements

- [ ] Prayer time notifications
- [ ] Qibla direction compass
- [ ] Prayer time history
- [ ] Multiple location bookmarks
- [ ] Offline mode with cached data
- [ ] Widget for home screen
- [ ] Dark/Light theme toggle

## Development Notes

### Testing
- Unit tests for API integration
- UI tests for critical user flows
- Manual testing on various screen sizes

### Performance
- Efficient API calls with caching
- Minimal network requests
- Smooth UI transitions
- Optimized for battery usage

## Installation

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle dependencies
4. Run on device or emulator (API level 24+)

## Permissions Required

- `INTERNET` - For API calls
- `ACCESS_NETWORK_STATE` - For network status
- `ACCESS_FINE_LOCATION` - For current location (optional)
- `ACCESS_COARSE_LOCATION` - For current location (optional)

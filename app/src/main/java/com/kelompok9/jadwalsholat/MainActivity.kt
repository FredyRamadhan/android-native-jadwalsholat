package com.kelompok9.jadwalsholat
/* Kelompok 9
* CANDRA RIFKI EKA FEBRIANSYAH		L0123039
* FEBRIAN ERNANDA ARYAPUTRA 		L0123056
* FREDY TRI RAMADHAN 	    		L0123057 */
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kelompok9.jadwalsholat.ui.screens.DaftarHariIniScreen
import com.kelompok9.jadwalsholat.ui.screens.DaftarShalatSunnahScreen
import com.kelompok9.jadwalsholat.ui.screens.HomeScreen
import com.kelompok9.jadwalsholat.ui.screens.LocationSettingsScreen
import com.kelompok9.jadwalsholat.ui.screens.ShalatSunnahDetailScreen
import com.kelompok9.jadwalsholat.ui.theme.JadwalSholatTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JadwalSholatTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("home") {
                            HomeScreen(
                                onTodayPrayerClick = { navController.navigate("daftar_hari_ini") },
                                onShalatSunnahClick = { navController.navigate("daftar_shalat_sunnah") },
                                onLocationSettingsClick = { navController.navigate("location_settings") }
                            )
                        }
                        composable("daftar_hari_ini") {
                            DaftarHariIniScreen()
                        }
                        composable("daftar_shalat_sunnah") {
                            DaftarShalatSunnahScreen(
                                onShalatSunnahClick = { shalatSunnahId ->
                                    navController.navigate("shalat_sunnah_detail/$shalatSunnahId")
                                }
                            )
                        }
                        composable("location_settings") {
                            LocationSettingsScreen(
                                onBackClick = { navController.popBackStack() },
                                onLocationSelected = { navController.popBackStack() }
                            )
                        }
                        composable("shalat_sunnah_detail/{shalatSunnahId}") { backStackEntry ->
                            val shalatSunnahId = backStackEntry.arguments?.getString("shalatSunnahId")?.toIntOrNull() ?: 0
                            ShalatSunnahDetailScreen(
                                shalatSunnahId = shalatSunnahId,
                                onBackClick = { navController.popBackStack() }
                            )
                        }

                    }
                }
            }
        }
    }
}



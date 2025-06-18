package com.kelompok9.jadwalsholat.ui.theme

import androidx.compose.ui.graphics.Color

// Custom color palette
val DarkBlue = Color(0xFF353A47)      // #353A47 - Primary dark
val TealBlue = Color(0xFF82AEB1)      // #82AEB1 - Secondary/accent
val LightPink = Color(0xFFF7EBEC)     // #F7EBEC - Background/surface
val Red = Color(0xFFD33F49)          // #D33F49 - Error/accent

// Light theme colors
val Primary = DarkBlue
val Secondary = TealBlue
val Background = LightPink
val Surface = Color.White
val Error = Red
val OnPrimary = Color.White
val OnSecondary = Color.White
val OnBackground = DarkBlue
val OnSurface = DarkBlue
val OnError = Color.White

// Additional colors for better contrast
val SurfaceVariant = Color(0xFFF0F0F0)
val OnSurfaceVariant = DarkBlue.copy(alpha = 0.8f)
val PrimaryContainer = TealBlue.copy(alpha = 0.2f)
val OnPrimaryContainer = DarkBlue
val SecondaryContainer = LightPink
val OnSecondaryContainer = DarkBlue
package com.konkuk.kuit_kac.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.konkuk.kuit_kac.R

// Set of Material typography styles to start with

val DungGeunMo = FontFamily(
    Font(R.font.dunggeunmo)
)
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = DungGeunMo,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ))
val DungGeunMo35 = TextStyle(
    fontFamily = DungGeunMo,
    fontSize = 35.sp
)

val DungGeunMo27 = TextStyle(
    fontFamily = DungGeunMo,
    fontSize = 27.sp
)

val DungGeunMo24 = TextStyle(
    fontFamily = DungGeunMo,
    fontSize = 24.sp
)
val DungGeunMo20 = TextStyle(
    fontFamily = DungGeunMo,
    fontSize = 20.sp
)
val DungGeunMo17 = TextStyle(
    fontFamily = DungGeunMo,
    fontSize = 17.sp
)
val DungGeunMo15 = TextStyle(
    fontFamily = DungGeunMo,
    fontSize = 15.sp
)
val DungGeunMo12 = TextStyle(
    fontFamily = DungGeunMo,
    fontSize = 12.sp
)
/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/

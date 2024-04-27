package theme.foundation

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import composedemo.composeapp.generated.resources.Res
import composedemo.composeapp.generated.resources.ibm_plex_sans
import composedemo.composeapp.generated.resources.ibm_plex_sans_bold
import composedemo.composeapp.generated.resources.ibm_plex_sans_medium
import composedemo.composeapp.generated.resources.ibm_plex_sans_semibold
import composedemo.composeapp.generated.resources.metropolis_bold
import composedemo.composeapp.generated.resources.metropolis_extra_bold
import composedemo.composeapp.generated.resources.metropolis_light
import composedemo.composeapp.generated.resources.metropolis_medium
import composedemo.composeapp.generated.resources.metropolis_regular
import composedemo.composeapp.generated.resources.metropolis_semi_bold
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

class Typography {
    private val defaultFontFamily: FontFamily
        @Composable get() = Metro

    val display: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 48.sp,
            lineHeight = 64.sp
        )
    val largeTitle: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            lineHeight = 44.sp
        )
    val title01: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 36.sp
        )
    val title02: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp
        )
    val title03: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    val subtitle01: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    val subtitle02: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    val subtitle03: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.02.sp
        )
    val overline: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.025.sp
        )
    val body01: TextStyle
        @Composable get() = TextStyle(
            fontFamily = Ibm,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    val body02: TextStyle
        @Composable get() = TextStyle(
            fontFamily = Ibm,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    val caption: TextStyle
        @Composable get() = TextStyle(
            fontFamily = defaultFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp
        )
}

@OptIn(ExperimentalResourceApi::class)
private val Metro: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.metropolis_light, FontWeight.Light),
        Font(Res.font.metropolis_regular, FontWeight.Normal),
        Font(Res.font.metropolis_medium, FontWeight.Medium),
        Font(Res.font.metropolis_semi_bold, FontWeight.SemiBold),
        Font(Res.font.metropolis_bold, FontWeight.Bold),
        Font(Res.font.metropolis_extra_bold, FontWeight.ExtraBold)
    )

@OptIn(ExperimentalResourceApi::class)
private val Ibm
    @Composable get() = FontFamily(
        Font(Res.font.ibm_plex_sans, FontWeight.Normal),
        Font(Res.font.ibm_plex_sans_medium, FontWeight.Medium),
        Font(Res.font.ibm_plex_sans_semibold, FontWeight.SemiBold),
        Font(Res.font.ibm_plex_sans_bold, FontWeight.Bold),
    )

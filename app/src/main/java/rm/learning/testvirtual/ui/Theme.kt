package rm.learning.testvirtual.ui



import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val HerbalColorPrimary = Color(0xFF2F6B2F)
private val HerbalColorSecondary = Color(0xFF86B17A)
private val HerbalBg = Color(0xFFF6FBF6)
private val HerbalSurface = Color(0xFFEDF7EE)
private val HerbalAccent = Color(0xFFF6A623)
private val HerbalMuted = Color(0xFF5A6B5A)


private val HerbalLightColors = lightColorScheme(
    primary = HerbalColorPrimary,
    onPrimary = Color.White,
    secondary = HerbalColorSecondary,
    onSecondary = Color.White,
    background = HerbalBg,
    surface = HerbalSurface,
    onSurface = HerbalMuted,
    surfaceVariant = Color(0xFFE8F3E8),
    tertiary = HerbalAccent
)


private val HerbalTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 26.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontSize = 20.sp
    )
)


@Composable
fun HerbalTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = HerbalLightColors,
        typography = HerbalTypography,
        shapes = Shapes(
            small = RoundedCornerShape(10.dp),
            medium = RoundedCornerShape(16.dp),
            large = RoundedCornerShape(24.dp)
        ),
        content = content
    )
}
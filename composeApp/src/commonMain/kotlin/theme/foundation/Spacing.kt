package theme.foundation

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val twoExtraSmall: Dp = 4.dp,
    val extraSmall: Dp = 8.dp,
    val small: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val large: Dp = 24.dp,
    val extraLarge: Dp = 32.dp,
    val twoExtraLarge: Dp = 40.dp,
    val threeExtraLarge: Dp = 48.dp
)

@Composable
fun Dp.Spacer() {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(this))
}

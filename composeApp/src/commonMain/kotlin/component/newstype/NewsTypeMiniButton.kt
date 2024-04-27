package component.newstype

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.Appearance

@Composable
fun NewsTypeMiniButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(NewsTypeMiniButtonSize)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = Appearance.typography.caption,
            color = Appearance.colors.textPrimary,
            maxLines = 1
        )
    }
}

val NewsTypeMiniButtonSize = 56.dp
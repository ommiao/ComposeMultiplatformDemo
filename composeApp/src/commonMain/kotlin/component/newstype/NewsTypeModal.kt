package component.newstype

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import model.NewsType
import theme.Appearance

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun NewsTypeModal(
    modifier: Modifier = Modifier,
    newsTypes: List<NewsType>,
    selected: NewsType,
    onChoose: (NewsType) -> Unit
) {
    FlowRow(
        modifier = modifier
            .height(NewsTypeModalSize)
            .verticalScroll(rememberScrollState())
            .padding(Appearance.spacing.medium),
        maxItemsInEachRow = 2,
        horizontalArrangement = Arrangement.spacedBy(Appearance.spacing.extraSmall),
        verticalArrangement = Arrangement.spacedBy(Appearance.spacing.extraSmall)
    ) {
        newsTypes.forEach {
            NewsTypePill(
                newsType = it,
                selected = selected == it
            ) {
                onChoose.invoke(it)
            }
        }
    }
}

@Composable
private fun NewsTypePill(
    modifier: Modifier = Modifier,
    newsType: NewsType,
    selected: Boolean,
    onClick: () -> Unit
) {
    val (backgroundColor, textColor) = if (selected) {
        Appearance.colors.backgroundSelected to Appearance.colors.textInverted
    } else {
        Appearance.colors.backgroundSecondaryBase to Appearance.colors.textSecondary
    }
    Text(
        modifier = modifier.clip(CircleShape)
            .clickable(onClick = onClick)
            .background(backgroundColor)
            .padding(
                vertical = Appearance.spacing.twoExtraSmall,
                horizontal = Appearance.spacing.small
            ),
        text = newsType.name,
        color = textColor,
        maxLines = 1
    )
}

val NewsTypeModalSize = 200.dp

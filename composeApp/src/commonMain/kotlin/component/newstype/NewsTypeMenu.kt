package component.newstype

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import model.NewsType
import theme.Appearance
import theme.clickableWithoutRipple

@Composable
fun NewsTypeMenu(
    modifier: Modifier = Modifier,
    newsTypes: List<NewsType>,
    selected: NewsType,
    onChangeNewsType: (NewsType) -> Unit
) {
    var isModalOpen by remember {
        mutableStateOf(false)
    }
    val radius by animateDpAsState(
        targetValue = if (isModalOpen) {
            Appearance.radius.large
        } else {
            NewsTypeMiniButtonSize / 2
        }
    )
    Box(
        modifier = modifier
            .fillMaxSize()
            .let {
                if (isModalOpen) {
                    it.clickableWithoutRipple {
                        isModalOpen = false
                    }
                } else {
                    it
                }
            }
    ) {
        Surface(
            modifier = Modifier.align(Alignment.BottomEnd),
            shape = RoundedCornerShape(radius),
            elevation = 8.dp
        ) {
            AnimatedContent(targetState = isModalOpen) { open ->
                if (open) {
                    NewsTypeModal(
                        newsTypes = newsTypes,
                        selected = selected
                    ) {
                        if (selected != it){
                            onChangeNewsType.invoke(it)
                        }
                        isModalOpen = false
                    }
                } else {
                    NewsTypeMiniButton(text = selected.name) {
                        isModalOpen = true
                    }
                }
            }
        }
    }
}
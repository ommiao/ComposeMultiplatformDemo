package screen.newslist

import Action
import LocalActionHandler
import State
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import component.newstype.NewsTypeMenu
import model.NewsItem
import screen.loading.LoadingScreen
import theme.Appearance
import theme.foundation.Spacer

@Composable
fun NewsListScreen(
    modifier: Modifier = Modifier,
    state: State,
    onClick: ((NewsItem) -> Unit)? = null
) {
    val actionHandler = LocalActionHandler.current
    Box(modifier.background(Appearance.colors.backgroundSecondary)) {

        Crossfade(targetState = state.isLoadingNewsList) { loading ->
            if (loading) {
                LoadingScreen()
            } else {
                if (state.newsListState != null) {
                    NewsListContent(
                        state.newsListState.newsList,
                        state.newsListState.selectedNews
                    ) {
                        actionHandler(Action.OpenNewsItem(it))
                        onClick?.invoke(it)
                    }
                }
            }
        }

        if (state.newsTypeState != null) {
            val newsTypeState = state.newsTypeState
            NewsTypeMenu(
                modifier = Modifier
                    .padding(Appearance.spacing.large),
                newsTypes = newsTypeState.newsTypes,
                selected = newsTypeState.selectedNewsType
            ) {
                actionHandler(Action.ChangeNewsType(it))
            }
        }
    }
}

@Composable
private fun NewsListContent(
    newsList: List<NewsItem>,
    selectedNews: NewsItem,
    onClick: (NewsItem) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = Appearance.spacing.medium,
            vertical = Appearance.spacing.medium
        ),
        verticalArrangement = Arrangement.spacedBy(Appearance.spacing.small)
    ) {
        items(newsList) {
            NewsItem(newsItem = it, selected = it == selectedNews, onClick = { onClick(it) })
        }
        item {
            Spacer(modifier = Modifier.size(88.dp))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NewsItem(newsItem: NewsItem, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) {
            Appearance.colors.backgroundInformation
        } else {
            Appearance.colors.backgroundPrimaryHigh
        }
    )
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(Appearance.radius.medium),
        backgroundColor = backgroundColor,
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                modifier = Modifier.fillMaxWidth().aspectRatio(1.8f),
                model = newsItem.imgList.firstOrNull(),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Appearance.spacing.extraSmall.Spacer()
            Text(
                modifier = Modifier.padding(horizontal = Appearance.spacing.small),
                text = newsItem.title,
                color = Appearance.colors.textPrimary,
                style = Appearance.typography.subtitle01,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Appearance.spacing.twoExtraSmall.Spacer()
            Text(
                modifier = Modifier.padding(horizontal = Appearance.spacing.small),
                text = newsItem.digest,
                color = Appearance.colors.textSecondary,
                style = Appearance.typography.subtitle02,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Appearance.spacing.extraSmall.Spacer()
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.padding(horizontal = Appearance.spacing.small),
                    text = newsItem.postTime,
                    color = Appearance.colors.textSecondary,
                    style = Appearance.typography.caption,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.padding(horizontal = Appearance.spacing.small),
                    text = newsItem.source,
                    color = Appearance.colors.textSecondary,
                    style = Appearance.typography.caption,
                    maxLines = 1
                )
            }
            Appearance.spacing.small.Spacer()
        }
    }
}

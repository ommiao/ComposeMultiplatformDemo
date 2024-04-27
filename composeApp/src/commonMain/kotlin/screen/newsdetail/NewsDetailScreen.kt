package screen.newsdetail

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import model.NewsDetail
import model.NewsItem
import theme.Appearance
import theme.foundation.Spacer

@Composable
fun NewsDetailScreen(
    modifier: Modifier = Modifier,
    newsItem: NewsItem? = null,
    isLoadingDetail: Boolean,
    newsDetail: NewsDetail? = null
) {
    Column(modifier.fillMaxSize().background(Appearance.colors.backgroundPrimaryBase)) {
        newsItem?.let {
            Crossfade(targetState = newsItem) {
                NewsHeader(newsItem = it)
            }
        }
        if (newsDetail == null || isLoadingDetail) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "...",
                    color = Appearance.colors.textSecondary,
                    style = Appearance.typography.body01
                )
            }
        } else {
            NewsDetailContent(newsDetail = newsDetail)
        }
    }
}

@Composable
private fun NewsHeader(newsItem: NewsItem) {
    Column(modifier = Modifier.padding(horizontal = Appearance.spacing.medium)) {
        Appearance.spacing.medium.Spacer()
        Text(
            text = newsItem.title,
            style = Appearance.typography.title01,
            color = Appearance.colors.textPrimary
        )
        Appearance.spacing.small.Spacer()
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = newsItem.postTime,
                color = Appearance.colors.textSecondary,
                style = Appearance.typography.caption,
                maxLines = 1
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = newsItem.source,
                color = Appearance.colors.textSecondary,
                style = Appearance.typography.caption,
                maxLines = 1
            )
        }
        Appearance.spacing.extraSmall.Spacer()
        Divider(color = Appearance.colors.borderDisabled)
        Appearance.spacing.extraSmall.Spacer()
    }
}

@Composable
private fun NewsDetailContent(newsDetail: NewsDetail) {
    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = Appearance.spacing.medium,
            vertical = Appearance.spacing.large
        ),
        verticalArrangement = Arrangement.spacedBy(Appearance.spacing.extraSmall)
    ) {
        items(newsDetail.items) {
            when (it.type) {
                "text" -> NewsDetailText(text = it.content)
                "img" -> NewsDetailImage(imageUrl = it.imageUrl!!)
                "video" -> NewsDetailVideo(videoUrl = it.videoUrl!!)
            }
        }
        item {
            Spacer(modifier = Modifier.size(88.dp))
        }
    }
}

@Composable
private fun NewsDetailText(text: String) {
    Text(
        modifier = Modifier.padding(vertical = Appearance.spacing.extraSmall),
        text = text,
        color = Appearance.colors.textPrimary,
        style = Appearance.typography.body01
    )
}

@Composable
private fun NewsDetailImage(imageUrl: String) {
    AsyncImage(
        modifier = Modifier.fillMaxWidth().aspectRatio(1.8f),
        model = imageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun NewsDetailVideo(videoUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Appearance.colors.backgroundSecondaryBase)
            .border(color = Appearance.colors.borderDisabled, width = 1.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Video not supported!",
            color = Appearance.colors.textSecondary,
            style = Appearance.typography.body01
        )
    }
}

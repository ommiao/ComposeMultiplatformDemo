import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.CloseNewsButton
import kotlinx.coroutines.launch
import model.NewsDetail
import model.NewsItem
import model.NewsType
import org.jetbrains.compose.ui.tooling.preview.Preview
import screen.loading.LoadingScreen
import screen.newsdetail.NewsDetailScreen
import screen.newslist.NewsListScreen
import theme.Appearance
import theme.Theme

val LocalActionHandler = staticCompositionLocalOf<(Action) -> Unit> { error("not provide") }

@Composable
@Preview
fun App() {
    val newsApiClient = remember {
        NewsApiClient()
    }
    val scope = rememberCoroutineScope()
    var state by remember {
        mutableStateOf(State())
    }

    val openNewsItem = { newsItem: NewsItem ->
        state = state.copy(
            newsListState = state.newsListState!!.copy(
                selectedNews = newsItem
            )
        )
        scope.launch {
            state = state.copy(
                isLoadingNewsDetail = true
            )
            val newsDetailResponse = newsApiClient.getNewsDetail(newsItem.newsId)
            state = state.copy(
                isLoadingNewsDetail = false,
                newsDetail = newsDetailResponse.data
            )
        }
    }

    val changeNewsType = { newsType: NewsType ->
        state = state.copy(
            newsTypeState = state.newsTypeState!!.copy(
                selectedNewsType = newsType
            )
        )
        scope.launch {
            state = state.copy(
                isLoadingNewsList = true
            )
            val newsListResponse = newsApiClient.getNewsList(newsType.id)
            state = state.copy(
                isLoadingNewsList = false,
                newsListState = state.newsListState?.copy(
                    newsList = newsListResponse.data
                ) ?: NewsListState(
                    newsList = newsListResponse.data,
                    selectedNews = newsListResponse.data.first()
                )
            )
            openNewsItem(newsListResponse.data.first())
        }
    }

    val actionHandler: (Action) -> Unit = { action: Action ->
        when (action) {
            is Action.ChangeNewsType -> {
                changeNewsType(action.newsType)
            }

            is Action.OpenNewsItem -> {
                openNewsItem(action.newsItem)
            }
        }
    }


    LaunchedEffect(Unit) {
        val newsTypes = newsApiClient.getNewsTypes()
        state = State(
            isLoadingNewsTypes = false,
            newsTypeState = NewsTypeState(
                newsTypes = newsTypes.data,
                selectedNewsType = newsTypes.data.first()
            )
        )
        actionHandler(Action.ChangeNewsType(newsTypes.data.first()))
    }
    CompositionLocalProvider(LocalActionHandler provides actionHandler) {
        AppContent(state)
    }
}

@Composable
private fun AppContent(state: State) {
    Theme {
        BoxWithConstraints {
            Crossfade(targetState = state.isLoadingNewsTypes) { loading ->
                if (loading) {
                    LoadingScreen()
                } else {
                    if (maxWidth.value > 450) {
                        Row {
                            NewsListScreen(state = state, modifier = Modifier.widthIn(max = 300.dp))
                            NewsDetailScreen(
                                newsItem = state.newsListState?.selectedNews,
                                newsDetail = state.newsDetail,
                                modifier = Modifier.fillMaxWidth(),
                                isLoadingDetail = state.isLoadingNewsDetail
                            )
                        }
                    } else {
                        var detailModalState by remember {
                            mutableStateOf(DetailModalState.Closed)
                        }
                        Box {
                            NewsListScreen(state = state, modifier = Modifier.fillMaxWidth()) {
                                detailModalState = DetailModalState.Open
                            }
                            AnimatedVisibility(
                                visible = detailModalState == DetailModalState.Open,
                                enter = slideInVertically { fullHeight -> fullHeight },
                                exit = slideOutVertically { fullHeight -> fullHeight }
                            ) {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    NewsDetailScreen(
                                        newsItem = state.newsListState?.selectedNews,
                                        newsDetail = state.newsDetail,
                                        modifier = Modifier.fillMaxWidth(),
                                        isLoadingDetail = state.isLoadingNewsDetail
                                    )
                                    CloseNewsButton(
                                        modifier = Modifier.align(Alignment.BottomEnd)
                                            .padding(Appearance.spacing.large)
                                    ) {
                                        detailModalState = DetailModalState.Closed
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class State(
    val isLoadingNewsTypes: Boolean = true,
    val newsTypeState: NewsTypeState? = null,
    val isLoadingNewsList: Boolean = true,
    val newsListState: NewsListState? = null,
    val isLoadingNewsDetail: Boolean = true,
    val newsDetail: NewsDetail? = null
)

data class NewsTypeState(
    val newsTypes: List<NewsType>,
    val selectedNewsType: NewsType
)

data class NewsListState(
    val newsList: List<NewsItem>,
    val selectedNews: NewsItem
)

sealed class Action {
    data class ChangeNewsType(val newsType: NewsType) : Action()
    data class OpenNewsItem(val newsItem: NewsItem) : Action()
}

enum class DetailModalState {
    Open, Closed
}
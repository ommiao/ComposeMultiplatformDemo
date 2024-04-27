import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import model.NewsDetailResponse
import model.NewsListResponse
import model.NewsTypeResponse

class NewsApiClient {

    private val baseUrl = "https://www.mxnzp.com/api/news"
    private val newsTypesUrl = "$baseUrl/types/v2"
    private val newsListUrl = "$baseUrl/list/v2?typeId={typeId}&page=1"
    private val newsDetailUrl = "$baseUrl/details/v2?newsId={newsId}"
    private val client = HttpClient()

    suspend fun getNewsTypes(): NewsTypeResponse {
        delay(1000)
        val result = client.getAsText(newsTypesUrl)
        return Json.decodeFromString(NewsTypeResponse.serializer(), result)
    }

    suspend fun getNewsList(typeId: Int): NewsListResponse {
        delay(1000)
        val result =
            client.getAsText(newsListUrl.withApiToken().replace("{typeId}", typeId.toString()))
        return Json.decodeFromString(NewsListResponse.serializer(), result)
    }

    suspend fun getNewsDetail(newsId: String): NewsDetailResponse {
        delay(1000)
        val result = client.getAsText(newsDetailUrl.withApiToken().replace("{newsId}", newsId))
        return Json.decodeFromString(NewsDetailResponse.serializer(), result)
    }

    private suspend fun HttpClient.getAsText(url: String): String {
        return get(url.withApiToken()).bodyAsText().also {
            println("HttpClient get from url: $url, result: $it")
        }
    }

    private fun String.withApiToken(): String {
        return if (contains("?")) {
            "$this&app_id=$apiAppId&app_secret=$apiAppSecret"
        } else {
            "$this?app_id=$apiAppId&app_secret=$apiAppSecret"
        }
    }

    companion object {
        private val apiAppId = "xxx"
        private val apiAppSecret = "xxx"
    }
}


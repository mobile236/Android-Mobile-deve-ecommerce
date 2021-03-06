package cn.seabornlee.dogify.api

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.SharedImmutable

private val jsonConfiguration = Json {
    prettyPrint = true
    ignoreUnknownKeys = true
}

@SharedImmutable
private val httpClient = HttpClient {
    install(JsonFeature) {
        serializer =
            KotlinxSerializer(jsonConfiguration)
    }
    install(Logging) {
        logger = Logger.SIMPLE
        level = LogLevel.ALL
    }
}

internal abstract class KtorApi {
    val client = httpClient
    fun HttpRequestBuilder.apiUrl(path: String) {
        url {
            takeFrom("https://dog.ceo")
            path("api", path)
        }
    }
}
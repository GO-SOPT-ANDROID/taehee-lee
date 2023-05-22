package org.android.go.sopt.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KakaoSearchResponseDto(
    @SerialName("meta")
    val meta: Meta,
    @SerialName("documents")
    val documents: List<Document>,
) {
    @Serializable
    data class Document(
        @SerialName("datetime")
        val datetime: String,
        @SerialName("contents")
        val contents: String,
        @SerialName("title")
        val title: String,
        @SerialName("url")
        val url: String,
    )

    @Serializable
    data class Meta(
        @SerialName("total_count")
        val totalCount: Long,
        @SerialName("pageable_count")
        val pageableCount: Long,
        @SerialName("is_end")
        val isEnd: Boolean,
    )
}
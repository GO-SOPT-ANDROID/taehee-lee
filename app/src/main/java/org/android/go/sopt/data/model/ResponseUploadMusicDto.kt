package org.android.go.sopt.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.domain.model.Music

@Serializable
data class ResponseUploadMusicDto(
    @SerialName("data")
    val `data`: List<MusicData>,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
) {
    @Serializable
    data class MusicData(
        @SerialName("singer")
        val singer: String,
        @SerialName("title")
        val title: String,
        @SerialName("url")
        val url: String
    )

    fun toMusicFormat() = data.map { music ->
        Music(
            singer = music.singer,
            title = music.title,
            url = music.url
        )
    }

}

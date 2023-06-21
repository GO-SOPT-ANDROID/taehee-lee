package org.android.go.sopt.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.android.go.sopt.domain.model.Music

@Serializable
data class ResponsePlayListDto(
    @SerialName("data")
    val `data`: Data,
    @SerialName("message")
    val message: String,
    @SerialName("status")
    val status: Int
) {
    @Serializable
    data class Data(
        @SerialName("musicList")
        val musicList: List<Music>
    ) {
        @Serializable
        data class Music(
            @SerialName("singer")
            val singer: String,
            @SerialName("title")
            val title: String,
            @SerialName("url")
            val url: String
        )
    }

    fun toMusicList() = data.musicList.map { music ->
        Music(
            singer = music.singer,
            title = music.title,
            url = music.url
        )
    }
}

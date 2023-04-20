package ru.lm.itunesplayer.model.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Rustam Galimov (mailto:rustam.galimoff@yandex.ru)
 * @since 20.01.2020
 */
data class ItunesResponse (
    @SerializedName("resultCount")
    @Expose
    val resultCount: Int,

    @SerializedName("results")
    @Expose
    val results: List<ItunesMedia>
)

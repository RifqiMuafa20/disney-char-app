package com.D121211063.aplikasilistkarakterdisney.model

import android.os.Parcelable
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.parcelize.Parcelize

//@Serializable
//data class Characterm(
//    @SerialName("data")
//    val data: Data?,
//    @SerialName("info")
//    val info: Info?
//)

@Parcelize
@Serializable
data class Characterm(
    @SerialName("allies")
    val allies: List<String>?,
    @SerialName("createdAt")
    val createdAt: String?, // 2021-04-12T01:31:30.547Z
    @SerialName("enemies")
    val enemies: List<String>?,
    @SerialName("films")
    val films: List<String>?,
    @SerialName("_id")
    val id: Int, // 112
    @SerialName("imageUrl")
    val imageUrl: String?, // https://static.wikia.nocookie.net/disney/images/d/d3/Vlcsnap-2015-05-06-23h04m15s601.png
    @SerialName("name")
    val name: String, // Achilles
    @SerialName("parkAttractions")
    val parkAttractions: List<String>?,
    @SerialName("shortFilms")
    val shortFilms: List<String>?,
    @SerialName("sourceUrl")
    val sourceUrl: String?, // https://disney.fandom.com/wiki/Achilles_(Hercules)
    @SerialName("tvShows")
    val tvShows: List<String>?,
    @SerialName("updatedAt")
    val updatedAt: String?, // 2021-12-20T20:39:18.033Z
    @SerialName("url")
    val url: String?, // https://api.disneyapi.dev/characters/112
    @SerialName("__v")
    val v: Int?, // 0
    @SerialName("videoGames")
    val videoGames: List<String>?
): Parcelable

//@Serializable
//data class Info(
//    @SerialName("count")
//    val count: Int?, // 50
//    @SerialName("nextPage")
//    val nextPage: String?, // http://api.disneyapi.dev/character?page=2&pageSize=50
//    @SerialName("previousPage")
//    val previousPage: String?, // null
//    @SerialName("totalPages")
//    val totalPages: Int? // 149
//)
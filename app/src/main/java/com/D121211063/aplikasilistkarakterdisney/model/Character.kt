package com.D121211063.aplikasilistkarakterdisney.model

import android.os.Parcelable
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.parcelize.Parcelize

@Parcelize
@Serializable
data class Characterm(
    @SerialName("films")
    val films: List<String>,
    @SerialName("_id")
    val id: Int, // 112
    @SerialName("imageUrl")
    val imageUrl: String, // https://static.wikia.nocookie.net/disney/images/d/d3/Vlcsnap-2015-05-06-23h04m15s601.png
    @SerialName("name")
    val name: String, // Achilles
    @SerialName("sourceUrl")
    val sourceUrl: String, // https://disney.fandom.com/wiki/Achilles_(Hercules)
): Parcelable

package com.example.vinilos.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Musician(
    val id: Int,
    val name: String,
    val image: String,
) : Parcelable {

    constructor(
        id: Int,
        name: String,
        image: String,
        birthDate: String?,
    ) : this(id, name, image)

}

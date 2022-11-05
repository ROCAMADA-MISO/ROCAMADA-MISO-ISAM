package com.example.vinilos.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Band(
    val id: Int,
    val name: String,
    val image: String,
) : Parcelable {

    constructor(
        id: Int,
        name: String,
        image: String,
        creationDate: String?,
    ) : this(id, name, image)

}

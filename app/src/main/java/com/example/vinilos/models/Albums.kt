package com.example.vinilos.models

import java.sql.Date

data class Albums(
    val id: Int,
    val name:String,
    val cover:String,
    val releaseDate: Date?,
    val description:String,
    val genre:String,
    val recordLabel:String
)


private var isSelected = false

fun getSelected(): Boolean {
    return isSelected
}

fun setSelected(selected: Boolean) {
    isSelected = selected
}
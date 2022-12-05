package com.example.vinilos.models

class Comment (
val description:String,
val rating:Int,
val collector: Collector
)


data class Collector (
    val id: Int
)
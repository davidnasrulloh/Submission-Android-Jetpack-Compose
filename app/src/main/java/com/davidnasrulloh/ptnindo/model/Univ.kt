package com.davidnasrulloh.ptnindo.model

data class Univ(
    val id: Int,
    val name: String,
    val description: String,
    val location: String,
    val imgUrl: String,
    var isFavorite: Boolean = false,
)

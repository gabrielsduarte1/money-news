package br.com.moneynews.model

data class Quote(
    val name: String,
    val code: String,
    val value: String,
    val change: String,
    val isFavorite: Boolean = false
)
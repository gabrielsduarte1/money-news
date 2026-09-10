package br.com.moneynews.model

data class QuoteResponse(
    val code: String,
    val codein: String,
    val name: String,
    val bid: String,
    val pctChange: String
)
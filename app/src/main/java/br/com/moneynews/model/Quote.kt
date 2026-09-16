package br.com.moneynews.model

data class Quote(
    val name: String,
    val code: String,
    val value: String,
    val change: String
)

fun QuoteResponse.toQuote(): Quote {
    return Quote(
        name = name,
        code = "$code/$codein",
        value = "R$ $bid",
        change = if (pctChange.startsWith("-")) "$pctChange%" else "+$pctChange%"
    )
}
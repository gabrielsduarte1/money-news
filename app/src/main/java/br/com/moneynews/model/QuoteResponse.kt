package br.com.moneynews.model

import java.text.NumberFormat
import java.util.Locale

data class QuoteResponse(
    val code: String,
    val codein: String,
    val name: String,
    val bid: String,
    val pctChange: String
)

fun QuoteResponse.toQuote(): Quote {
    return Quote(
        name = name,
        code = "$code/$codein",
        value = "R$ ${formatarNumero(bid)}",
        change = if (pctChange.startsWith("-")) {
            "${formatarNumero(pctChange)}%"
        } else {
            "+${formatarNumero(pctChange)}%"
        }
    )
}

private fun formatarNumero(texto: String): String {
    val numero = texto.toDoubleOrNull() ?: 0.0
    val formatador = NumberFormat.getNumberInstance(Locale("pt", "BR"))
    formatador.minimumFractionDigits = 2
    formatador.maximumFractionDigits = 2
    return formatador.format(numero)
}
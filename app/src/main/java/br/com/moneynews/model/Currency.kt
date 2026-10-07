package br.com.moneynews.model

import androidx.annotation.StringRes
import br.com.moneynews.R

enum class Currency (
    val code: String,
    @StringRes val nameRes: Int
) {
    BRL("BRL", R.string.converter_currency_brl),
    USD("USD", R.string.converter_currency_usd),
    EUR("EUR", R.string.converter_currency_eur)
}
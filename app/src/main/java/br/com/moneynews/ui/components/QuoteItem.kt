package br.com.moneynews.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.moneynews.ui.theme.MoneyNewsTheme
import androidx.compose.ui.res.stringResource
import br.com.moneynews.R
import br.com.moneynews.ui.theme.FavoriteStar

@Composable
fun QuoteItem(
    name: String,
    code: String,
    value: String,
    change: String,
    isFavorite: Boolean = false,
    onFavoriteClick: () -> Unit = {}
) {
    val corDoTexto = if (change.startsWith("-")) {
        Color(0xFFC62828)
    } else {
        Color(0xFF2E7D32)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable(
                        interactionSource = null,
                        indication = null,
                        onClick = onFavoriteClick
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.StarBorder,
                    contentDescription = if (isFavorite) stringResource(R.string.quote_item_remove_favorite) else stringResource(R.string.quote_item_add_favorite),
                    tint = if (isFavorite) FavoriteStar else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                )
            }
            Column {
                Text(
                    text = name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = code,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = value,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = change,
                fontSize = 12.sp,
                color = corDoTexto
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteItemPreview() {
    MoneyNewsTheme {
        QuoteItem(
            name = "Dólar comercial",
            code = "USD/BRL",
            value = "R$ 5,42",
            change = "+0,32%",
            isFavorite = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteItemEuroPreview() {
    MoneyNewsTheme {
        QuoteItem(
            name = "Euro",
            code = "EUR/BRL",
            value = "R$ 5,89",
            change = "-0,15%"
        )
    }
}
package com.tprobius.binformation.presentation.screens.searchscreen.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tprobius.binformation.data.entities.Binfo
import java.util.*

@Composable
fun BinDataCard(binfo: Binfo?) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 20.dp,
            vertical = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            CardScheme(binfo)
            CardBrand(binfo)
            CardType(binfo)
            IsPrepaid(binfo)
            CardNumber(binfo)
            Country(binfo, context)
            Bank(binfo)
            BankUrl(binfo, context)
            BankPhone(binfo, context)
        }
    }
}

@Composable
private fun CardScheme(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Row {
        Text(
            text = "SCHEME/NETWORK: ",
            color = Color.LightGray
        )
        Text(
            text = binfo?.scheme?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } ?: "",
            color = Color.Gray
        )
    }
}

@Composable
private fun CardBrand(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Row {
        Text(
            text = "BRAND: ",
            color = Color.LightGray
        )
        Text(
            text = binfo?.brand ?: "",
            color = Color.Gray
        )
    }

}

@Composable
private fun CardType(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Row {
        Text(
            text = "TYPE: ",
            color = Color.LightGray
        )
        Text(
            text = binfo?.type?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } ?: "",
            color = Color.Gray
        )
    }
}

@Composable
private fun IsPrepaid(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Row() {
        Text(
            text = "PREPAID: ",
            color = Color.LightGray
        )
        Text(
            text = binfo?.prepaid?.let { if (it) "Yes" else "No" } ?: "",
            color = Color.Gray
        )
    }

}

@Composable
private fun CardNumber(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Column() {
        Text(
            text = "CARD NUMBER: ",
            color = Color.LightGray
        )
        Row {
            Text(
                text = "length: ",
                color = Color.LightGray
            )
            Text(
                text = "${binfo?.number?.let { it.length ?: "" } ?: ""}\t",
                color = Color.Gray
            )
            Text(
                text = "luhn: ",
                color = Color.LightGray
            )
            Text(
                text = binfo?.number?.let { if (it.luhn == true) "Yes" else "No" } ?: "",
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun Country(
    binfo: Binfo?,
    context: Context
) {
    Spacer(modifier = Modifier.padding(4.dp))
    Column {
        Row {
            Text(
                text = "COUNTRY: ",
                color = Color.LightGray
            )
            Text(
                text = binfo?.country?.let { (it.name ?: "") } ?: "",
                modifier = Modifier
                    .clickable {
                        val mapIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(
                                "geo:${binfo?.country?.let { it.latitude ?: "" }}," +
                                        "${binfo?.country?.let { it.longitude ?: "" }}"
                            )
                        )
                        context.startActivity(mapIntent)
                    },
                color = Color.Gray
            )
        }

        Row(
            modifier = Modifier
                .clickable {
                    val mapIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            "geo:${binfo?.country?.let { it.latitude ?: "" }}," +
                                    "${binfo?.country?.let { it.longitude ?: "" }}"
                        )
                    )
                    context.startActivity(mapIntent)
                }) {
            Text(
                text = "(latitude: ",
                color = Color.LightGray
            )
            Text(
                text = "${binfo?.country?.let { (it.latitude ?: "") } ?: " "}",
                color = Color.Gray
            )
            Text(
                text = ", longitude: ",
                color = Color.LightGray
            )
            Text(
                text = "${binfo?.country?.let { (it.longitude ?: "") } ?: " "}",
                color = Color.Gray
            )
            Text(
                text = ")",
                color = Color.LightGray
            )
        }
    }
}

@Composable
private fun Bank(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Row() {
        Text(
            text = "BANK: ",
            color = Color.LightGray
        )
        Text(
            text = binfo?.bank?.let { it.name ?: "" } ?: "",
            color = Color.Gray
        )
    }
}

@Composable
private fun BankUrl(
    binfo: Binfo?,
    context: Context
) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        modifier = Modifier
            .clickable {
                val httpIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://${binfo?.bank?.let { it.url ?: "" }}")
                )
                context.startActivity(httpIntent)
            },
        text = binfo?.bank?.let { it.url ?: "" } ?: "",
        color = Color.Blue
    )
}

@Composable
private fun BankPhone(
    binfo: Binfo?,
    context: Context
) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        modifier = Modifier
            .clickable {
                val telIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("tel:${binfo?.bank?.let { it.phone ?: "" }}")
                )
                context.startActivity(telIntent)
            },
        text = binfo?.bank?.let { it.phone ?: "" } ?: "",
        color = Color.Gray
    )
}
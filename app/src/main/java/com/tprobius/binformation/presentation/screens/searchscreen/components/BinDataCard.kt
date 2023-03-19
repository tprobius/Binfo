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
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
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
    Text(
        text = "SCHEME/NETWORK: ${
            binfo?.scheme?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } ?: ""
        }"
    )
}

@Composable
private fun CardBrand(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "BRAND: ${binfo?.brand ?: ""}"
    )
}

@Composable
private fun CardType(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "TYPE: ${
            binfo?.type?.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            } ?: ""
        }"
    )
}

@Composable
private fun IsPrepaid(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "PREPAID: ${binfo?.prepaid?.let { if (it) "Yes" else "No" } ?: ""}"
    )
}

@Composable
private fun CardNumber(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "CARD NUMBER:\n" +
                "length: ${binfo?.number?.let { it.length ?: "" } ?: ""}\t" +
                "luhn: ${binfo?.number?.let { if (it.luhn == true) "Yes" else "No" } ?: ""}"
    )
}

@Composable
private fun Country(
    binfo: Binfo?,
    context: Context
) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
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
        text = "COUNTRY: ${binfo?.country?.let { (it.name ?: "") } ?: ""}\n" +
                "(latitude: ${binfo?.country?.let { (it.latitude ?: "") } ?: ""}, " +
                "longitude: ${binfo?.country?.let { (it.longitude ?: "") } ?: ""})"
    )
}

@Composable
private fun Bank(binfo: Binfo?) {
    Spacer(modifier = Modifier.padding(4.dp))
    Text(
        text = "BANK: ${binfo?.bank?.let { it.name ?: "" } ?: ""}"
    )
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
        text = binfo?.bank?.let { it.url ?: "" } ?: ""
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
        text = binfo?.bank?.let { it.phone ?: "" } ?: ""
    )
}
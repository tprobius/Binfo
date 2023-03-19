package com.tprobius.binformation.presentation.screens.searchscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tprobius.binformation.data.entities.Binfo
import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.presentation.screens.homescreen.HomeScreenEvent
import com.tprobius.binformation.presentation.viewmodel.BinfoViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen() {
    val viewModel = hiltViewModel<BinfoViewModel>()
    val searchTextState by viewModel.searchTextState
    val binfo = viewModel.binfo.observeAsState().value

    Scaffold(
        topBar = {
            SearchAppBar(
                text = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
//                onCloseClicked = {
//                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
//                },
                viewModel = viewModel
            )

        }
    ) {
        BinDataCard(binfo = binfo)
    }
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
//    onCloseClicked: () -> Unit,
    viewModel: BinfoViewModel
) {
    val focusManager = LocalFocusManager.current
    val maxNum = 8
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it.take(maxNum))
                if (it.length > maxNum) {
                    focusManager.moveFocus(FocusDirection.Down)
                    viewModel.getBinfo(text.toInt())
                }
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Enter BIN gere",
                )
            },
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
//                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.getBinfo(text.toInt())
                    viewModel.onEvent(HomeScreenEvent.InsertBin(bin = Bin(number = text.toInt())))
                }
            ),
            visualTransformation = InputMask(),
        )
    }
}

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
            binfo?.scheme?.let { Text(text = "SCHEME/NETWORK: ${it.capitalize()}") }
            binfo?.brand?.let { Text(text = "BRAND: ${it}") }
            binfo?.type?.let { Text(text = "TYPE: ${it.capitalize()}") }
            binfo?.prepaid?.let { Text(text = "PREPAID: ${if (it) "Yes" else "No"}") }
            binfo?.number?.let { Text(text = "CARD NUMBER:\nlength: ${it.length ?: ""}\tluhn: ${if (it.luhn == true) "Yes" else "No"}") }
            binfo?.country?.let {
                Text(
                    modifier = Modifier
                        .clickable {
                            val mapIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("geo:${it.latitude ?: ""},${it.longitude ?: ""}")
                            )
                            context.startActivity(mapIntent)
                        },
                    text = "COUNTRY: ${it.name ?: ""}\n(latitude: ${it.latitude ?: ""}, longitude: ${it.longitude ?: ""})"
                )
            }
            binfo?.bank?.let { Text(text = "BANK: ${it.name ?: ""}") }
            binfo?.bank?.let {
                Text(
                    modifier = Modifier
                        .clickable {
                            val httpIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("http://${it.url ?: ""}")
                            )
                            context.startActivity(httpIntent)
                        },
                    text = it.url ?: ""
                )
            }
            binfo?.bank?.let {
                Text(
                    modifier = Modifier
                        .clickable {
                            val telIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("tel:${it.phone ?: ""}")
                            )
                            context.startActivity(telIntent)
                        },
                    text = it.phone ?: ""
                )
            }
        }
    }
}
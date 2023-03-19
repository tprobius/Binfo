package com.tprobius.binformation.ui.screens.homescreen

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tprobius.binformation.data.api.model.Binformation
import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.ui.navigation.Screens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val binformation = viewModel.binformation.observeAsState().value

    val searchWidgetState by viewModel.searchWidgetState
    val searchTextState by viewModel.searchTextState

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.HistoryScreen.route)
                },
                backgroundColor = Color(0xDDFFCC00)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Add new note"
                )
            }
        },
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                onCloseClicked = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                },
                onSearchTriggered = {
                    viewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                },
                viewModel = viewModel
            )
        }
    ) {
        BinDataCard(binformation = binformation)
    }
}

@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchTriggered: () -> Unit,
    viewModel: HomeScreenViewModel
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = onSearchTriggered
            )
        }
        SearchWidgetState.OPENED -> {
            SearchAppBar(
                text = searchTextState,
                onTextChange = onTextChange,
                onCloseClicked = onCloseClicked,
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun DefaultAppBar(onSearchClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "BInformation"
            )
        },
        actions = {
            IconButton(
                onClick = { onSearchClicked() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                )
            }
        }
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    viewModel: HomeScreenViewModel
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
                    viewModel.getBinformation(text.toInt())
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
                            onCloseClicked()
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
                    viewModel.getBinformation(text.toInt())
                    viewModel.insertNumber(number = Bins(number = text.toInt()))
                }
            ),
            visualTransformation = InputMask(),
        )
    }
}

@Composable
fun BinDataCard(binformation: Binformation?) {

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            binformation?.scheme?.let { Text(text = "SCHEME/NETWORK: ${it.capitalize()}") }
            binformation?.brand?.let { Text(text = "BRAND: ${it}") }
            binformation?.type?.let { Text(text = "TYPE: ${it.capitalize()}") }
            binformation?.prepaid?.let { Text(text = "PREPAID: ${if (it) "Yes" else "No"}") }
            binformation?.number?.let { Text(text = "CARD NUMBER:\nlength: ${it.length ?: ""}\tluhn: ${if (it.luhn == true) "Yes" else "No"}") }
            binformation?.country?.let {
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
            binformation?.bank?.let { Text(text = "BANK: ${it.name ?: ""}, ${it.city ?: ""}\n${it.url ?: ""}\n${it.phone ?: ""}") }
            binformation?.bank?.let {
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
            binformation?.bank?.let {
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
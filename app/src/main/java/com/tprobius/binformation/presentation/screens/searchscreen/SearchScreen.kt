package com.tprobius.binformation.presentation.screens.historyscreen

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tprobius.binformation.presentation.navigation.Screens
import com.tprobius.binformation.presentation.screens.searchscreen.SearchScreenViewModel
import com.tprobius.binformation.presentation.screens.searchscreen.components.BinDataCard
import com.tprobius.binformation.presentation.screens.searchscreen.components.SearchAppBar
import com.tprobius.binformation.ui.theme.DarkYellow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<SearchScreenViewModel>()
    val searchTextState by viewModel.searchTextState
    val binfo = viewModel.binfo.observeAsState().value

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screens.HistoryScreen.route)
            },
            backgroundColor = DarkYellow
        ) {
            Icon(
                imageVector = Icons.Default.History,
                tint = Color.White,
                contentDescription = "Search"
            )
        }
    },
        topBar = {
            SearchAppBar(
                text = searchTextState,
                onTextChange = {
                    viewModel.updateSearchTextState(newValue = it)
                },
                viewModel = viewModel
            )
        }
    ) {
        BinDataCard(binfo = binfo)
    }
}
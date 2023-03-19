package com.tprobius.binformation.presentation.screens.searchscreen

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tprobius.binformation.presentation.screens.searchscreen.components.BinDataCard
import com.tprobius.binformation.presentation.screens.searchscreen.components.SearchAppBar
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
                viewModel = viewModel
            )

        }
    ) {
        BinDataCard(binfo = binfo)
    }
}
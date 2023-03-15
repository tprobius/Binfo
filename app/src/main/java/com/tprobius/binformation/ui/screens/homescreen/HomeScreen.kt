package com.tprobius.binformation.ui.screens.homescreen

import android.annotation.SuppressLint
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val binformation = viewModel.binformation.observeAsState().value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color(254, 221, 0),
                onClick = {

                })
            {
                Icon(
                    imageVector = Icons.Filled.Add,
                    tint = Color.White,
                    contentDescription = "Add new note"
                )
            }
        }
    ) {
        if (binformation != null) {
            Text(text = binformation.brand)
        }
    }
}
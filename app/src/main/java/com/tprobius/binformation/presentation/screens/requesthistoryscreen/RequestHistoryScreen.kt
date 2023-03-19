package com.tprobius.binformation.presentation.screens.requesthistoryscreen

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.tprobius.binformation.presentation.navigation.Screens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RequestHistoryScreen(navController: NavHostController) {

    val viewModel = hiltViewModel<RequestHistoryScreenViewModel>()
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Request history",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.bins) { bins ->
                    BinItem(
                        bin = bins,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(Screens.HomeScreen.route)
                            },
                        onDeleteClick = {
                            viewModel.onEvent(RequestHistoryEvent.DeleteNote(bins))
                            scope.launch {
                                val result = scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Bin deleted",
                                    actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(RequestHistoryEvent.RestoreNote)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
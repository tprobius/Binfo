package com.tprobius.binformation.ui.screens.homescreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tprobius.binformation.data.api.ApiConstants
import com.tprobius.binformation.data.api.model.Binformation
import com.tprobius.binformation.ui.theme.BinformationTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val binformation = viewModel.binformation.observeAsState().value

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Top
        ) {
//            Text(
//                modifier = Modifier.padding(vertical = 8.dp),
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                text = "ADD_NEW_NOTE"
//            )

////        if (binformation != null) {
////            Text(text = binformation.bank.name)
////        }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = ApiConstants.END_POINT,
                onValueChange = {
//                    text = it
//                    isButtonEnable = title.isNotEmpty() && content.isNotEmpty()
                },
                placeholder = { Text(text = "Enter card BIN") },
//                isError = title.isEmpty()
            )
            Button(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(Alignment.End),
//                enabled = isButtonEnable,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(254, 221, 0)),
                onClick = {

                }
            ) {
                Text(
                    color = Color.White,
                    fontSize = 16.sp,
                    text = "CHECK"
                )
            }
            CardInfo(binformation)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CardInfo(binformation: Binformation?) {
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row {
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 2.dp)
                        .fillMaxWidth(0.5f),
                    value = binformation?.scheme ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(
                            text = "SCHEME/NETWORK",
                            fontSize = 16.sp
                        )
                    }
                )
                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 2.dp, end = 16.dp)
                        .fillMaxWidth(),
                    value = binformation?.type ?: "",
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text(
                            text = "TYPE",
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotesScreen() {
    BinformationTheme() {
        HomeScreen(navController = rememberNavController())
    }
}
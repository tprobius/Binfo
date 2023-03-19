package com.tprobius.binformation.presentation.screens.searchscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.presentation.screens.homescreen.HomeScreenEvent
import com.tprobius.binformation.presentation.screens.searchscreen.InputMask
import com.tprobius.binformation.presentation.viewmodel.BinfoViewModel
import com.tprobius.binformation.ui.theme.LightYellow

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
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
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = LightYellow,
                cursorColor = Color.DarkGray
            ),
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
                    color = Color.DarkGray,
                    text = "Enter BIN here",
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
                        tint = Color.DarkGray
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.DarkGray
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
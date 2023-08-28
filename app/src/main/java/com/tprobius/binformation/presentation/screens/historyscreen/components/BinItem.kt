package com.tprobius.binformation.presentation.screens.historyscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tprobius.binformation.domain.entities.Bin

@Composable
fun BinItem(
    bin: Bin,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = modifier,
        elevation = 1.dp
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp),
                fontSize = 16.sp,
                maxLines = 1,
                text = bin.number.toString()
            )
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete bin",
                    tint = Color.DarkGray
                )
            }
        }
    }
}
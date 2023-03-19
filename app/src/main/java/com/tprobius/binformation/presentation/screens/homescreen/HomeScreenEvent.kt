package com.tprobius.binformation.presentation.screens.homescreen

import com.tprobius.binformation.domain.entities.Bin

sealed class HomeScreenEvent {
    data class InsertBin(val bin: Bin) : HomeScreenEvent()
    data class DeleteBin(val bin: Bin) : HomeScreenEvent()

    object RestoreBin : HomeScreenEvent()
}

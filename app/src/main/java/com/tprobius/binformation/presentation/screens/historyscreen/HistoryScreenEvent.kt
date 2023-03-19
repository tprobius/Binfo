package com.tprobius.binformation.presentation.screens.historyscreen

import com.tprobius.binformation.domain.entities.Bin

sealed class HistoryScreenEvent {
    data class DeleteBin(val bin: Bin) : HistoryScreenEvent()
    object RestoreBin : HistoryScreenEvent()
}

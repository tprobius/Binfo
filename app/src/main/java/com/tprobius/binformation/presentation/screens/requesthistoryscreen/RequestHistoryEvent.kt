package com.tprobius.binformation.presentation.screens.requesthistoryscreen

import com.tprobius.binformation.domain.entities.Bin

sealed class RequestHistoryEvent {
    data class DeleteNote(val bin: Bin) : RequestHistoryEvent()
    object RestoreNote : RequestHistoryEvent()
}

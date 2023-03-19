package com.tprobius.binformation.ui.screens.requesthistoryscreen

import com.tprobius.binformation.domain.model.Bin

sealed class RequestHistoryEvent {
    data class DeleteNote(val bin: Bin) : RequestHistoryEvent()
    object RestoreNote : RequestHistoryEvent()
}

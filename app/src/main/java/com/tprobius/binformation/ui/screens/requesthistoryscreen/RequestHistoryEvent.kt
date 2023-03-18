package com.tprobius.binformation.ui.screens.requesthistoryscreen

import com.tprobius.binformation.domain.model.Bins

sealed class RequestHistoryEvent {
    data class DeleteNote(val bin: Bins) : RequestHistoryEvent()
    object RestoreNote : RequestHistoryEvent()
}

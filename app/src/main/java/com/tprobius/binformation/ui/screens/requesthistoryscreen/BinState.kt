package com.tprobius.binformation.ui.screens.requesthistoryscreen

import com.tprobius.binformation.domain.model.Bins

data class BinState(
val bins: List<Bins> = emptyList()
)

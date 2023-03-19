package com.tprobius.binformation.ui.screens.requesthistoryscreen

import com.tprobius.binformation.domain.model.Bin

data class BinState(
val bins: List<Bin> = emptyList()
)

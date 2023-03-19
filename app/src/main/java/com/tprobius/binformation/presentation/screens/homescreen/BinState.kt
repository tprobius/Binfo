package com.tprobius.binformation.presentation.screens.homescreen

import com.tprobius.binformation.domain.entities.Bin

data class BinState(
    val bins: List<Bin> = emptyList()
)

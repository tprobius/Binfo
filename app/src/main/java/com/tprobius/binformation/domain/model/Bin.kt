package com.tprobius.binformation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bin(
    val number: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int = number
)

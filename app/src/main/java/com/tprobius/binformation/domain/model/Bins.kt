package com.tprobius.binformation.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Bins(
    val number: Int,
    @PrimaryKey(autoGenerate = false)
    val id: Int = number
)

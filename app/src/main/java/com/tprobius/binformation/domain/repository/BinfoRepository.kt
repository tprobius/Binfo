package com.tprobius.binformation.domain.repository

import com.tprobius.binformation.domain.entities.Bin

interface BinfoRepository {
    suspend fun getBins(): List<Bin>
    suspend fun getBin(number: Int): Bin?
    suspend fun insertBin(bin: Bin)
    suspend fun deleteBin(bin: Bin)
}
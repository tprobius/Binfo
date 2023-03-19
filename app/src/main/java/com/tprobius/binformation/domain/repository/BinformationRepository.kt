package com.tprobius.binformation.domain.repository

import com.tprobius.binformation.domain.entities.Bin
import kotlinx.coroutines.flow.Flow

interface BinformationRepository {
    fun getBins(): Flow<List<Bin>>
    suspend fun getBin(number: Int): Bin?
    suspend fun insertBin(bin: Bin)
    suspend fun deleteBin(bin: Bin)
}
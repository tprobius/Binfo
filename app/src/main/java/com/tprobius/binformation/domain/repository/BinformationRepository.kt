package com.tprobius.binformation.domain.repository

import com.tprobius.binformation.domain.model.Bins
import kotlinx.coroutines.flow.Flow

interface BinformationRepository {
    fun getNumbers(): Flow<List<Bins>>
    suspend fun getNumber(number: Int): Bins?
    suspend fun insertNumber(number: Bins)
    suspend fun deleteNumber(number: Bins)
}
package com.tprobius.binformation.domain.usecases

import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository
import kotlinx.coroutines.flow.Flow

class GetBins(private val repository: BinformationRepository) {
    operator fun invoke(): Flow<List<Bin>> {
        return repository.getBins()
    }
}
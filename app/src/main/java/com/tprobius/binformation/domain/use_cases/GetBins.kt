package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository
import kotlinx.coroutines.flow.Flow

class GetBins(private val repository: BinformationRepository) {
    operator fun invoke(): Flow<List<Bin>> {
        return repository.getBins()
    }
}
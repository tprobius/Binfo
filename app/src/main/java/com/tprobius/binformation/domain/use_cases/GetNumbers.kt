package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.repository.BinformationRepository
import kotlinx.coroutines.flow.Flow

class GetNumbers(private val repository: BinformationRepository) {
    operator fun invoke(): Flow<List<Bins>> {
        return repository.getNumbers()
    }
}
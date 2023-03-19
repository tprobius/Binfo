package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository

class GetBin(private val repository: BinformationRepository) {
    suspend operator fun invoke(number: Int): Bin? {
        return repository.getBin(number)
    }
}
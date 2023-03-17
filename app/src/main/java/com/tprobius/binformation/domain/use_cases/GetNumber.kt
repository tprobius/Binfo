package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.repository.BinformationRepository

class GetNumber(private val repository: BinformationRepository) {
    suspend operator fun invoke(number: Int): Bins? {
        return repository.getNumber(number)
    }
}
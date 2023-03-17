package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.repository.BinformationRepository

class DeleteNumber(private val repository: BinformationRepository) {
    suspend operator fun invoke(number: Bins) {
        return repository.deleteNumber(number)
    }
}
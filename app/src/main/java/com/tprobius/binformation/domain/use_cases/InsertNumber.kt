package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.repository.BinformationRepository

class InsertNumber(private val repository: BinformationRepository) {
    suspend operator fun invoke(number: Bins?) {
        if (number != null) {
            repository.insertNumber(number)
        }
    }
}
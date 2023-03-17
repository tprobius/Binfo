package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.repository.BinformationRepository

class GetNumbers(private val repository: BinformationRepository) {
    operator fun invoke(): List<Bins> {
        return repository.getNumbers()
    }
}
package com.tprobius.binformation.domain.usecases

import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository

class GetBin(private val repository: BinformationRepository) {
    suspend operator fun invoke(number: Int): Bin? {
        return repository.getBin(number)
    }
}
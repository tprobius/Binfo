package com.tprobius.binformation.domain.usecases

import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository

class InsertBin(private val repository: BinformationRepository) {
    suspend operator fun invoke(bin: Bin?) {
        if (bin != null) {
            repository.insertBin(bin)
        }
    }
}
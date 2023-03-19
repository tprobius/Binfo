package com.tprobius.binformation.domain.use_cases

import com.tprobius.binformation.domain.model.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository

class DeleteBin(private val repository: BinformationRepository) {
    suspend operator fun invoke(bin: Bin) {
        return repository.deleteBin(bin)
    }
}
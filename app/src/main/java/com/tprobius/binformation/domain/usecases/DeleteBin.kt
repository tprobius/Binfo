package com.tprobius.binformation.domain.usecases

import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository

class DeleteBin(private val repository: BinformationRepository) {
    suspend operator fun invoke(bin: Bin) {
        return repository.deleteBin(bin)
    }
}
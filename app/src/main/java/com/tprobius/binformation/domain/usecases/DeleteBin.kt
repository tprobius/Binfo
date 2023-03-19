package com.tprobius.binformation.domain.usecases

import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinfoRepository

class DeleteBin(private val repository: BinfoRepository) {
    suspend operator fun invoke(bin: Bin) {
        return repository.deleteBin(bin)
    }
}
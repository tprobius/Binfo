package com.tprobius.binformation.domain.usecases

import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinfoRepository

class InsertBin(private val repository: BinfoRepository) {
    suspend operator fun invoke(bin: Bin?) {
        if (bin != null) {
            repository.insertBin(bin)
        }
    }
}
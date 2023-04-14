package com.tprobius.binformation.domain.usecases

import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinfoRepository

class GetBins(private val repository: BinfoRepository) {
    operator fun invoke(): List<Bin> {
        return repository.getBins()
    }
}
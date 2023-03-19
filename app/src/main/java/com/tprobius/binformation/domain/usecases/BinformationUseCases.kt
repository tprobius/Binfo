package com.tprobius.binformation.domain.usecases

data class BinformationUseCases(
    val insertBin: InsertBin,
    val deleteBin: DeleteBin,
    val getBin: GetBin,
    val getBins: GetBins
)

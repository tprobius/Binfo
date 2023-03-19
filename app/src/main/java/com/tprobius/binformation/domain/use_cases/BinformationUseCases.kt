package com.tprobius.binformation.domain.use_cases

data class BinformationUseCases(
    val insertBin: InsertBin,
    val deleteBin: DeleteBin,
    val getBin: GetBin,
    val getBins: GetBins
)

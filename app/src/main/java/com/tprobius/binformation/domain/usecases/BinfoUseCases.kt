package com.tprobius.binformation.domain.usecases

data class BinfoUseCases(
    val insertBin: InsertBin,
    val deleteBin: DeleteBin,
    val getBin: GetBin,
    val getBins: GetBins
)

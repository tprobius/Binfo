package com.tprobius.binformation.domain.use_cases

data class BinformationUseCases(
    val insertNumber: InsertNumber,
    val deleteNumber: DeleteNumber,
    val getNumber: GetNumber,
    val getNumbers: GetNumbers
)

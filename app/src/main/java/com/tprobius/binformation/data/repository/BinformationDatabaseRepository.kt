package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.data_source.BinformationDao
import com.tprobius.binformation.domain.model.Bin
import com.tprobius.binformation.domain.repository.BinformationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BinformationDatabaseRepository @Inject constructor(
    private val dao: BinformationDao
) : BinformationRepository {

    override fun getBins(): Flow<List<Bin>> {
        return dao.getBins()
    }

    override suspend fun getBin(number: Int): Bin? {
        return dao.getBin(number)
    }

    override suspend fun insertBin(bin: Bin) {
        return dao.insertBin(bin)
    }

    override suspend fun deleteBin(bin: Bin) {
        return dao.deleteBin(bin)
    }
}
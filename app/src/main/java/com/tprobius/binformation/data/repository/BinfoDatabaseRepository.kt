package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.db.BinfoDao
import com.tprobius.binformation.domain.entities.Bin
import com.tprobius.binformation.domain.repository.BinfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BinfoDatabaseRepository @Inject constructor(
    private val dao: BinfoDao
) : BinfoRepository {
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
package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.data_source.BinformationDao
import com.tprobius.binformation.domain.model.Bins
import com.tprobius.binformation.domain.repository.BinformationRepository

class BinformationDatabaseRepository(private val dao: BinformationDao) : BinformationRepository {

    override fun getNumbers(): List<Bins> {
        return dao.getNumbers()
    }

    override suspend fun getNumber(number: Int): Bins? {
        return dao.getNumber(number)
    }

    override suspend fun insertNumber(number: Bins) {
        return dao.insertNumber(number)
    }

    override suspend fun deleteNumber(number: Bins) {
        return dao.deleteNumber(number)
    }
}
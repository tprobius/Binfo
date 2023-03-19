package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.api.BinformationApi
import com.tprobius.binformation.data.entities.Binformation
import javax.inject.Inject

class BinformationApiRepository @Inject constructor(
    private val binformationApi: BinformationApi
) {
    suspend fun getBinformation(number: Int): Binformation {
        return binformationApi.getBinformation(number)
    }
}
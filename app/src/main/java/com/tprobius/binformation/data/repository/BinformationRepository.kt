package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.api.BinformationApi
import com.tprobius.binformation.data.api.model.Binformation
import javax.inject.Inject

class BinformationRepository @Inject constructor(
    private val binformationApi: BinformationApi
) {
    suspend fun getBinformation(): Binformation {
        return binformationApi.getBinformation()
    }
}
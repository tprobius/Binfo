package com.tprobius.binformation.data.repository

import com.tprobius.binformation.data.api.BinfoApi
import com.tprobius.binformation.data.entities.Binfo
import javax.inject.Inject

class BinfoApiRepository @Inject constructor(
    private val binfoApi: BinfoApi
) {
    suspend fun getBinfo(number: Int): Binfo {
        return binfoApi.getBinfo(number)
    }
}
package com.tprobius.binformation.data.api

import com.tprobius.binformation.data.api.model.Binformation
import retrofit2.http.GET

interface BinformationApi {
    @GET(ApiConstants.END_POINT)
    suspend fun getBinformation(): Binformation
}
package com.tprobius.binformation.data.api

import com.tprobius.binformation.data.api.model.Binformation
import retrofit2.http.GET

interface BinformationApi {
    @GET(ApiConstants.BASE_URL)
    suspend fun getBinformation(): Binformation
}
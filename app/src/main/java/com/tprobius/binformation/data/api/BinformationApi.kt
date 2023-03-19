package com.tprobius.binformation.data.api

import com.tprobius.binformation.data.api.model.Binformation
import retrofit2.http.GET
import retrofit2.http.Path

interface BinformationApi {
    @GET("{number}")
    suspend fun getBinformation(
        @Path("number") number: Int
    ): Binformation
}

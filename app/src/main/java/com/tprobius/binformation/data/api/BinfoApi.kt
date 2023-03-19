package com.tprobius.binformation.data.api

import com.tprobius.binformation.data.entities.Binfo
import retrofit2.http.GET
import retrofit2.http.Path

interface BinfoApi {
    @GET("{number}")
    suspend fun getBinfo(
        @Path("number") number: Int
    ): Binfo
}
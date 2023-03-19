package com.tprobius.binformation.data.db

import androidx.room.*
import com.tprobius.binformation.domain.entities.Bin
import kotlinx.coroutines.flow.Flow

@Dao
interface BinformationDao {
    @Query("SELECT * FROM bin")
    fun getBins(): Flow<List<Bin>>

    @Query("SELECT * FROM bin WHERE number = :number")
    suspend fun getBin(number: Int): Bin?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBin(number: Bin)

    @Delete
    suspend fun deleteBin(bin: Bin)
}
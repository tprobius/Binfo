package com.tprobius.binformation.data.data_source

import androidx.room.*
import com.tprobius.binformation.domain.model.Bins

@Dao
interface BinformationDao {
    @Query("SELECT * FROM bins")
    fun getNumbers(): List<Bins>

    @Query("SELECT * FROM bins WHERE number = :number")
    suspend fun getNumber(number: Int): Bins?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(number: Bins)

    @Delete
    suspend fun deleteNumber(number: Bins)
}
package com.tprobius.binformation.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tprobius.binformation.domain.model.Bin

@Database(entities = [Bin::class], version = 1)
abstract class BinformationDatabase : RoomDatabase() {
    abstract val binformationDao: BinformationDao

    companion object {
        const val DATABASE_NAME = "bins_db"
    }
}
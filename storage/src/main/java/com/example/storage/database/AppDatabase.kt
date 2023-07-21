package com.example.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.storage.database.AppDatabase.Companion.VERSION
import com.example.storage.database.dao.ReposDao
import com.example.storage.database.entity.RepoEntity

@Database(
    version = VERSION,
    exportSchema = false,
    entities = [RepoEntity::class, ]
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun getReposDao(): ReposDao


    companion object {
        const val VERSION = 1
        const val NAME = "database"
    }
}
package com.example.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storage.database.entity.RepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
 interface ReposDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertProducts(products: List<RepoEntity>)

    @Query("SELECT * from RepoEntity")
     fun getAllProducts(): Flow<List<RepoEntity>>



}
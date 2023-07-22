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
      fun insertProducts(repos: List<RepoEntity>)

    @Query("SELECT * from RepoEntity WHERE page = :page")
      fun getReposByPage(page :Int): List<RepoEntity>



}
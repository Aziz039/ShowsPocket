package com.example.abdulaziz.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ShowDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertShow(show: ShowEntity)

    @Query("SELECT * FROM shows")
    fun getAllShows(): LiveData<List<ShowEntity>>

    @Update
    suspend fun updateShow(show: ShowEntity)

    @Delete
    suspend fun deleteShow(show: ShowEntity)
}
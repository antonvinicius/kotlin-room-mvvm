package com.antonvinicius.eventlist.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.antonvinicius.eventlist.models.Participant

@Dao
interface ParticipantDao {
    @Query("SELECT * FROM participants")
    fun getAllParticipants(): LiveData<List<Participant>>

    @Insert
    suspend fun insertParticipant(participant: Participant)

    @Delete
    suspend fun deleteParticipant(participant: Participant)
}
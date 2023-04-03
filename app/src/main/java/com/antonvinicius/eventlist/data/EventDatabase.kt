package com.antonvinicius.eventlist.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antonvinicius.eventlist.data.dao.ParticipantDao
import com.antonvinicius.eventlist.models.Participant

@Database(entities = [
    Participant::class
], version = 1)
abstract class EventDatabase : RoomDatabase() {
    abstract fun participantDao(): ParticipantDao

    companion object {
        fun getInstance(context: Context): EventDatabase {
            Log.d("EventDatabase", "Database created")

            return Room.databaseBuilder(
                context,
                EventDatabase::class.java,
                "event-database"
            ).build()
        }
    }
}
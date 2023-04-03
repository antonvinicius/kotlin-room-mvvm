package com.antonvinicius.eventlist.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "participants")
data class Participant(
    var name: String
) {
    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
}

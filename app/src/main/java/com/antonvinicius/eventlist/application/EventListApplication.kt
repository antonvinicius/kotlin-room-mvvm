package com.antonvinicius.eventlist.application

import android.app.Application
import com.antonvinicius.eventlist.data.EventDatabase

class EventListApplication : Application() {
    val db: EventDatabase by lazy {
        EventDatabase.getInstance(this)
    }
}
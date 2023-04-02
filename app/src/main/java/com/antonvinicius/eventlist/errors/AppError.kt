package com.antonvinicius.eventlist.errors

data class AppError(
    override var message: String
) : Throwable()
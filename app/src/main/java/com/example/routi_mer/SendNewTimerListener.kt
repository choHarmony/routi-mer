package com.example.routi_mer

interface SendNewTimerListener {
    fun sendTimerData(t: String, d: String, sec: String, set: String, one: String, full: String)
}
package com.sulavtimsina.jetpackdemo.data.remote.model

data class Fairings(
    val recovered: Boolean,
    val recovery_attempt: Boolean,
    val reused: Boolean,
    val ship: String
)
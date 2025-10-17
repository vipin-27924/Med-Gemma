package com.example.medgemma.model


data class HealthDataPayload(
    val userId: String,
    val steps: String,
    val heartRate: String,
    val bloodPressure: String,
    val bloodOxygen: String,
    val sleepDuration: String,
    val timestamp: Long = System.currentTimeMillis()
)
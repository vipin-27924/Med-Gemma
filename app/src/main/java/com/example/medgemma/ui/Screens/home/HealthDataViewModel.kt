// in app/src/main/java/com/example/medgemma/ui/Screens/home/HealthDataViewModel.kt

package com.example.medgemma.ui.Screens.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.records.*
import androidx.health.connect.client.request.ReadRecordsRequest
import androidx.health.connect.client.time.TimeRangeFilter
import com.example.medgemma.model.HealthDataPayload
import com.example.medgemma.network.ApiService
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

class HealthDataViewModel : ViewModel() {

    // Holds the state for the UI. Default values are "..."
    val steps = mutableStateOf("...")
    val heartRate = mutableStateOf("...")
    val bloodPressure = mutableStateOf("...")
    val bloodOxygen = mutableStateOf("...")
    val sleepDuration = mutableStateOf("...")

    private val apiService by lazy { ApiService.create() }

    // This is the main function to trigger all data fetching
    fun fetchData(healthConnectClient: HealthConnectClient) {
        viewModelScope.launch {
            val end = ZonedDateTime.now().toInstant()
            val start = end.minus(1, ChronoUnit.DAYS)

            readStepCount(healthConnectClient, start, end)
            readHeartRate(healthConnectClient, start, end)
            readBloodPressure(healthConnectClient, start, end)
            readOxygenSaturation(healthConnectClient, start, end)
            readSleepSessions(healthConnectClient, start, end)
        }
    }

    fun sendDataToBackend(userId: String) {
        viewModelScope.launch {
            val payload = HealthDataPayload(
                userId = userId,
                steps = steps.value,
                heartRate = heartRate.value,
                bloodPressure = bloodPressure.value,
                bloodOxygen = bloodOxygen.value,
                sleepDuration = sleepDuration.value
            )

            try {
                val response = apiService.sendHealthData(payload)
                if (response.isSuccessful) {
                    Log.d("ViewModel", "Data sent successfully!")
                } else {
                    Log.e("ViewModel", "API Error: ${response.code()} - ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Network Exception: ${e.message}")
            }
        }
    }


    private suspend fun readStepCount(client: HealthConnectClient, start: Instant, end: Instant) {
        try {
            val response = client.readRecords(ReadRecordsRequest(StepsRecord::class, timeRangeFilter = TimeRangeFilter.between(start, end)))
            steps.value = "${response.records.sumOf { it.count }} Steps"
        } catch (e: Exception) { steps.value = "N/A" }
    }

    private suspend fun readHeartRate(client: HealthConnectClient, start: Instant, end: Instant) {
        try {
            val response = client.readRecords(ReadRecordsRequest(HeartRateRecord::class, timeRangeFilter = TimeRangeFilter.between(start, end)))
            val lastHeartRate = response.records.lastOrNull()?.samples?.lastOrNull()?.beatsPerMinute
            heartRate.value = if (lastHeartRate != null) "$lastHeartRate BPM" else "N/A"
        } catch (e: Exception) { heartRate.value = "N/A" }
    }

    private suspend fun readBloodPressure(client: HealthConnectClient, start: Instant, end: Instant) {
        try {
            val response = client.readRecords(ReadRecordsRequest(BloodPressureRecord::class, timeRangeFilter = TimeRangeFilter.between(start, end)))
            val lastReading = response.records.lastOrNull()
            bloodPressure.value = if (lastReading != null) "${lastReading.systolic.inMillimetersOfMercury}/${lastReading.diastolic.inMillimetersOfMercury}" else "N/A"
        } catch (e: Exception) { bloodPressure.value = "N/A" }
    }

    private suspend fun readOxygenSaturation(client: HealthConnectClient, start: Instant, end: Instant) {
        try {
            val response = client.readRecords(ReadRecordsRequest(OxygenSaturationRecord::class, timeRangeFilter = TimeRangeFilter.between(start, end)))
            val lastReading = response.records.lastOrNull()
            bloodOxygen.value = if (lastReading != null) "${lastReading.percentage.value}%" else "N/A"
        } catch (e: Exception) { bloodOxygen.value = "N/A" }
    }

    private suspend fun readSleepSessions(client: HealthConnectClient, start: Instant, end: Instant) {
        try {
            val response = client.readRecords(ReadRecordsRequest(SleepSessionRecord::class, timeRangeFilter = TimeRangeFilter.between(start, end)))
            val totalSleepSeconds = response.records.sumOf { it.endTime.epochSecond - it.startTime.epochSecond }
            if (totalSleepSeconds > 0) {
                val hours = totalSleepSeconds / 3600
                val minutes = (totalSleepSeconds % 3600) / 60
                sleepDuration.value = "${hours}h ${minutes}m"
            } else {
                sleepDuration.value = "N/A"
            }
        } catch (e: Exception) { sleepDuration.value = "N/A" }
    }
}
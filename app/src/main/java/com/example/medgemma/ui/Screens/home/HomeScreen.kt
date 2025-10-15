package com.example.medgemma.ui.Screens.home

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.BloodPressureRecord
import androidx.health.connect.client.records.HeartRateRecord
import androidx.health.connect.client.records.OxygenSaturationRecord
import androidx.health.connect.client.records.SleepSessionRecord
import androidx.health.connect.client.records.StepsRecord
import com.example.medgemma.ui.Screens.home.homeComponents.BloodOxygenCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.BloodPressureCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.FitnessDetailsPreview
import com.example.medgemma.ui.Screens.home.homeComponents.HeartRateCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.SleepRateCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.StepCountCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.TopHeaderPreview


// --- THIS IS THE FIXED AND INTEGRATED SCREEN ---

@Composable
fun HealthDataScreen() {
    // 1. Define the permissions your app needs
    val permissions = setOf(
        HealthPermission.getReadPermission(StepsRecord::class),
        HealthPermission.getWritePermission(StepsRecord::class),
        HealthPermission.getReadPermission(HeartRateRecord::class),
        HealthPermission.getWritePermission(HeartRateRecord::class),
        HealthPermission.getReadPermission(OxygenSaturationRecord::class),
        HealthPermission.getWritePermission(OxygenSaturationRecord::class),
        HealthPermission.getReadPermission(BloodPressureRecord::class),
        HealthPermission.getWritePermission(BloodPressureRecord::class),
        HealthPermission.getReadPermission(SleepSessionRecord::class),
        HealthPermission.getWritePermission(SleepSessionRecord::class)
    )

    // 2. State to track whether permissions have been granted
    var hasPermissions by remember { mutableStateOf(false) }

    // 3. Get the HealthConnectClient instance
    val context = LocalContext.current
    val healthConnectClient = remember { HealthConnectClient.getOrCreate(context) }

    // 4. Create a launcher to request permissions
    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = healthConnectClient.permissionController.createRequestPermissionActivityContract(),
        onResult = { grantedPermissions: Set<String> ->
            if (grantedPermissions.containsAll(permissions)) {
                Log.d("HealthConnect", "All permissions granted.")
                hasPermissions = true
            } else {
                Log.d("HealthConnect", "Not all permissions were granted.")
                hasPermissions = false
            }
        }
    )

    // 5. Check for permissions automatically when the screen is first shown
    LaunchedEffect(Unit) {
        val granted = await healthConnectClient.permissionController.getGrantedPermissions()
        if (granted.containsAll(permissions)) {
            hasPermissions = true
        }
    }


    // 6. Display UI based on permission status
    if (hasPermissions) {
        // If permissions are granted, show the main dashboard
        HomeScreenContent()
    } else {
        // If permissions are not granted, show a button to request them
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Permissions are required to view health data.")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // Launch the permission request flow
                requestPermissionLauncher.launch(permissions)
            }) {
                Text("Request Permissions")
            }
        }
    }
}


// --- Your original UI content, now in its own composable ---
@Composable
fun HomeScreenContent() {
    Column(
        modifier = Modifier
            .background(color = Color.LightGray)
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        TopHeaderPreview()
        Spacer(modifier = Modifier.height(6.dp))
        FitnessDetailsPreview()
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .fillMaxWidth()

        ) {
            SleepRateCardPreview()
            Spacer(modifier = Modifier.width(6.dp))
            HeartRateCardPreview()
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .fillMaxWidth()

        ) {
            StepCountCardPreview()
            Spacer(modifier = Modifier.width(6.dp))
            BloodOxygenCardPreview()
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)
                .fillMaxWidth()

        ) {
            BloodPressureCardPreview()

        }
    }
}


// --- You can keep your preview for the UI content itself ---
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent()
}
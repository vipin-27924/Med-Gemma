
package com.example.medgemma.ui.Screens.home

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.contracts.HealthPermissionsRequestContract
import androidx.health.connect.client.permission.HealthPermission
import androidx.health.connect.client.records.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medgemma.ui.Screens.home.homeComponents.*

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(
    healthDataViewModel: HealthDataViewModel = viewModel()
) {
    val context = LocalContext.current

    val availabilityStatus = HealthConnectClient.getSdkStatus(context)
    if (availabilityStatus == HealthConnectClient.SDK_UNAVAILABLE) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Health Connect is not available on this device. Please install it from the Play Store.")
        }
        return
    }

    val healthConnectClient = remember { HealthConnectClient.getOrCreate(context) }
    var hasPermissions by remember { mutableStateOf(false) }

    val permissions = setOf(
        HealthPermission.getReadPermission(StepsRecord::class),
        HealthPermission.getReadPermission(HeartRateRecord::class),
        HealthPermission.getReadPermission(OxygenSaturationRecord::class),
        HealthPermission.getReadPermission(BloodPressureRecord::class),
        HealthPermission.getReadPermission(SleepSessionRecord::class)
    )

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = HealthPermissionsRequestContract(),
        onResult = { grantedPermissions ->
            hasPermissions = grantedPermissions.containsAll(permissions)
        }
    )

    LaunchedEffect(Unit) {
        val granted = healthConnectClient.permissionController.getGrantedPermissions()
        hasPermissions = granted.containsAll(permissions)
    }

    LaunchedEffect(hasPermissions) {
        if (hasPermissions) {
            healthDataViewModel.fetchData(healthConnectClient)
        }
    }

    if (hasPermissions) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("MedGemma Fitness") },
                    actions = {
                        Button(onClick = {
                            Log.d("HomeScreen", "Sync button clicked")
                            healthDataViewModel.sendDataToBackend("user_123")
                        }) {
                            Text("Sync")
                        }
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { FitnessDetailsPreview() }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StepCountCard(stepCount = healthDataViewModel.steps.value, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(16.dp))
                        HeartRateCard(heartRate = healthDataViewModel.heartRate.value, modifier = Modifier.weight(1f))
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BloodPressureCard(bloodPressure = healthDataViewModel.bloodPressure.value, modifier = Modifier.weight(1f))
                        Spacer(modifier = Modifier.width(16.dp))
                        BloodOxygenCard(bloodOxygen = healthDataViewModel.bloodOxygen.value, modifier = Modifier.weight(1f))
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    SleepRateCard(sleepRate = healthDataViewModel.sleepDuration.value)
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Permissions Required")
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "To display your health and fitness data, please grant access to Google Health Connect.",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { requestPermissionLauncher.launch(permissions) }) {
                Text("Grant Permissions")
            }
        }
    }
}
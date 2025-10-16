package com.example.medgemma.ui.Screens.home

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.health.connect.client.HealthConnectClient
import androidx.health.connect.client.contracts.HealthPermissionsRequestContract
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
import com.example.medgemma.ui.Screens.home.homeComponents.TopHeader


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen() {
    //declaring all the permissions which are required to access the health data
    val permission = setOf(
        HealthPermission.getReadPermission(StepsRecord::class),
        HealthPermission.getReadPermission(HeartRateRecord::class),
        HealthPermission.getReadPermission(OxygenSaturationRecord::class),
        HealthPermission.getReadPermission(BloodPressureRecord::class),
        HealthPermission.getReadPermission(SleepSessionRecord::class),
        HealthPermission.getWritePermission(SleepSessionRecord::class)
    )
    // declaring a variable to check if the  permission are granted or not
    var hasPermissions by remember { mutableStateOf(false) }


    // declaring the instance of the health connect
    val context = LocalContext.current
    val healthConnectClient = remember { HealthConnectClient.getOrCreate(context) }

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = HealthPermissionsRequestContract(),
        onResult = { grantedPermissions : Set<String> ->
            if (grantedPermissions.containsAll(permission)) {
                Log.d("HomeScreen", "All permissions granted.")
                hasPermissions = true
            } else {
                Log.d("HomeScreen", "Permissions denied.")
            }
        }
    )

    // this will check the permission Which are Required Every Single time The Activity(Composable) is Launched
    LaunchedEffect(Unit) {
        val granted = healthConnectClient.permissionController.getGrantedPermissions()
        hasPermissions = granted.containsAll(permission)
    }

    if (hasPermissions){
        Scaffold(
            topBar = {TopHeader()}
        ){
            paddingValues ->
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ){
                item {
                    FitnessDetailsPreview()
                    Spacer(modifier = Modifier.height(6.dp))
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        StepCountCardPreview()
                        Spacer(modifier = Modifier.width(16.dp))
                        HeartRateCardPreview()
                    }
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BloodPressureCardPreview()
                        Spacer(modifier = Modifier.width(16.dp))
                        BloodOxygenCardPreview()
                    }
                }
                item {
                    SleepRateCardPreview()
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }else {
        Column(
            Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(
                text = "Permissions Required"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "To display your health and fitness data, please grant access to Google Health Connect.",
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                requestPermissionLauncher.launch(permission)
            }) {
                Text("Grant Permissions")

            }
        }
    }
}
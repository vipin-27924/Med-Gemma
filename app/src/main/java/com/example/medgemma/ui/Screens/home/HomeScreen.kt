package com.example.medgemma.ui.Screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.medgemma.ui.Screens.home.homeComponents.BloodOxygenCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.BloodPressureCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.FitnessDetailsPreview
import com.example.medgemma.ui.Screens.home.homeComponents.HeartRateCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.SleepRateCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.StepCountCardPreview
import com.example.medgemma.ui.Screens.home.homeComponents.TopHeader
import com.example.medgemma.ui.Screens.home.homeComponents.TopHeaderPreview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreen(){
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
                .padding(top = 20.dp , bottom = 10.dp)
                .fillMaxWidth()

        ) {
            SleepRateCardPreview()
            Spacer(modifier = Modifier.width(6.dp))
            HeartRateCardPreview()
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .padding(top = 20.dp , bottom = 10.dp)
                .fillMaxWidth()

        ) {
            StepCountCardPreview()
            Spacer(modifier = Modifier.width(6.dp))
            BloodOxygenCardPreview()
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(
            modifier = Modifier
                .padding(top = 20.dp , bottom = 10.dp)
                .fillMaxWidth()

        ) {
            BloodPressureCardPreview()

        }



    }
}

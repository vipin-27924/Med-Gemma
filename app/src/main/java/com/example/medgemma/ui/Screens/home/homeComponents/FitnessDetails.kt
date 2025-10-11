package com.example.medgemma.ui.Screens.home.homeComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun fitnessDetails(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Fitness Details",
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FitnessDetailsPreview(){
    fitnessDetails()
}
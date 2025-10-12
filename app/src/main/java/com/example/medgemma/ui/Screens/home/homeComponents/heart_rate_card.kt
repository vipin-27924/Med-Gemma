package com.example.medgemma.ui.Screens.home.homeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.medgemma.R

@Composable
fun HeartRateCard() {

    Card(
        modifier = Modifier
            .height(200.dp)
            .width(170.dp),
        colors = CardDefaults.cardColors(Color.Gray)
    ) {
        Text(
            text = "Heart Rate",
            color = Color.Black,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .padding(10.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.heartrate),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
            )
        }
        Text(
            text = "77 BPM",
            color = Color.Black,
            fontFamily = FontFamily.Monospace,
            modifier = Modifier
                .padding(10.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
@Preview
fun HeartRateCardPreview() {
    HeartRateCard()
}
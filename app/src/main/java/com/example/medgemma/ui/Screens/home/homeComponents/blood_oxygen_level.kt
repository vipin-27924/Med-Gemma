package com.example.medgemma.ui.Screens.home.homeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource // Keep the import, even if the resource is mocked below

// Theme Colors (consistent with LoginScreen.kt)
private val MintGreen = Color(0xFF6DE8C3)

@Composable
fun BloodOxygenCard(bloodOxygen: String,
                    modifier: Modifier = Modifier) {

    Card(
        modifier = modifier
            .height(200.dp)
            .width(170.dp),
        // Applying rounded corners (16.dp for high rounding)
        shape = RoundedCornerShape(16.dp),
        // Using Mint Green as the theme color for the card background
        colors = CardDefaults.cardColors(containerColor = MintGreen),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Increased padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Title
            Text(
                text = "Blood Oxygen Level",
                color = Color.White, // White text for contrast on Mint Green
                fontWeight = FontWeight.SemiBold, // Using FontWeight instead of FontFamily.Monospace
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Icon Placeholder (using an emoji since R.drawable is not available)
            Text(
                text = "🩸", // Blood drop emoji for visual reference
                fontSize = 60.sp,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            // The original image reference (kept here but commented out, as R.drawable is external)
            /*
            Image(
                painter = painterResource(R.drawable.bloodoxygen),
                contentDescription = "Blood Oxygen Icon",
                modifier = Modifier.size(80.dp)
            )
            */

            Spacer(modifier = Modifier.height(8.dp))

            // Value
            Text(
                text = bloodOxygen,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold, // Very bold for the key metric
                fontSize = 32.sp
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BloodOxygenCardPreview() {
    // Providing a sample value to test the theme
    BloodOxygenCard(bloodOxygen = "98%")
}

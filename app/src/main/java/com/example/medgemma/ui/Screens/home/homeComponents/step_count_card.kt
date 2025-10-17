package com.example.medgemma.ui.Screens.home.homeComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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

// Theme Color (Consistent with other cards)
private val MintGreen = Color(0xFF6DE8C3)

/**
 * Component for displaying the daily Step Count, styled consistently
 * with the application theme.
 */
@Composable
fun StepCountCard(
    stepCount: String,
    modifier: Modifier = Modifier
) {

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
                text = "Step Count",
                color = Color.White, // White text for contrast on Mint Green
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth()
            )

            // Main Content Row (Icon and Count)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "👟", // Sneaker/Step emoji
                    fontSize = 40.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )

                // Value and Unit
                Column(horizontalAlignment = Alignment.Start) {
                    // Step Count Value
                    Text(
                        text = stepCount,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp,
                    )
                    Text(
                        text = "Steps",
                        color = Color.White.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StepCountCardPreview() {
    // Using a sample count for preview
    StepCountCard(stepCount = "12,240")
}

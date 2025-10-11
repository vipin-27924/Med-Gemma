package com.example.medgemma.ui.Screens.home.homeComponents

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.example.medgemma.R
import kotlinx.coroutines.launch

@Composable
fun SleepRateCard(){
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.sleep_rate))

    val scope = rememberCoroutineScope()
    val lottieAnimatable = rememberLottieAnimatable()

    LaunchedEffect(composition) {
        if (composition != null) {
            scope.launch {
                lottieAnimatable.animate(
                    composition = composition,
                    iteration = 1,
                    initialProgress = 0f
                )
            }
        }
    }
    Card(
        modifier = Modifier
            .height(250.dp)
            .width(170.dp),
    ){
        LottieAnimation(
            composition = composition,
            progress = { lottieAnimatable.progress },
            modifier = Modifier
                .size(100.dp)
        )

    }
}

@Preview
@Composable
fun SleepRateCardPreview(){
    SleepRateCard()

}
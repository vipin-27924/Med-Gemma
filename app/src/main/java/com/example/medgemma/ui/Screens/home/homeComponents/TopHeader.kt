package com.example.medgemma.ui.Screens.home.homeComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medgemma.R


@Composable
fun TopHeader(){
    Row(
        modifier = Modifier
            .padding(top = 15.dp , start = 15.dp , end = 15.dp, bottom = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.header_name),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace,
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopHeaderPreview(){
    TopHeader()
}
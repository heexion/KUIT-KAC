package com.konkuk.kuit_kac.presentation.home.component

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.presentation.navigation.Route
import com.konkuk.kuit_kac.ui.theme.DungGeunMo17

@Composable
fun ObservationBox(
    value: String,
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .height(52.73.dp)
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFA6A6A6))
                .align(Alignment.TopEnd)
        )
        Text(
            text = value,
            style = DungGeunMo17,
            color = Color(0xFF000000),
            lineHeight = 22.sp,
            modifier = Modifier.padding(top = 13.94.dp),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                modifier = Modifier
                    .padding(9.46.dp)
                    .size(31.5.dp)
                    .clickable(
                        onClick = {
                            navController.navigate(Route.HomeAnalysis.route)
                        }
                    ),
                painter = painterResource(id = R.drawable.ic_magnifier_home),
                contentDescription = "observation box Magnifier glass",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ObservationBoxPreview() {
    val navController = rememberNavController()
    ObservationBox(
        value = "공복 시간 적음",
        navController = navController
    )
}
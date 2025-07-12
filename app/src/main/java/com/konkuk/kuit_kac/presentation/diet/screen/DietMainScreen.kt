package com.konkuk.kuit_kac.presentation.diet.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konkuk.kuit_kac.R
import com.konkuk.kuit_kac.component.SelectButton
import com.konkuk.kuit_kac.core.util.context.bhp
import com.konkuk.kuit_kac.core.util.context.hp
import com.konkuk.kuit_kac.core.util.context.isp
import com.konkuk.kuit_kac.core.util.context.wp
import com.konkuk.kuit_kac.ui.theme.DungGeunMo20
import com.konkuk.kuit_kac.ui.theme.deepYellow

@Composable
fun DietMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(165f.bhp())
                .background(color = deepYellow)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16f.bhp()),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(28.8584f.wp(),28.8584f.bhp()),
                    painter = painterResource(R.drawable.ic_alcohol),
                    contentDescription = "utensils"
                )
                Text(
                    text = "식단",
                    style = DungGeunMo20,
                    fontSize = 20f.isp(),
                    color = Color(0xFF000000)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 17f.bhp())
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DietMainScreenPreview(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    DietMainScreen(navController = navController)
}
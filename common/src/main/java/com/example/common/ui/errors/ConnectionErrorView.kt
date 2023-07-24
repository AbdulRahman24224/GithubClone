package com.example.common.ui.errors

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.common.ui.sV
import com.example.githubclone.R

@Composable
 fun ConnectionErrorView(
    title:String  = stringResource(R.string.general_something_went_wrong),
    subtitle:String  = stringResource(R.string.general_network_alien),
    extraContent : @Composable () -> Unit
) {

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.Asset("error.json")
    )

    val progress by animateLottieCompositionAsState(
        composition,
        iterations = Int.MAX_VALUE
    )

    Box(
        Modifier
            .fillMaxSize()
            .padding(25.dp)
            .clip(RoundedCornerShape(20.dp))
    ) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            LottieAnimation(
                composition = composition,
                progress = progress,
                contentScale = ContentScale.Crop,
                modifier = Modifier.wrapContentSize()
            )
            sV(h = 20)


            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold ,
                    color =  MaterialTheme.colorScheme.secondary
                )
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onSecondary)
            )

            sV(h = 1)

            extraContent()
        }

    }
}

@Preview(showBackground = true)
@Composable private fun ConnectionErrorViewPreview() {
    ConnectionErrorView(
        title = stringResource(R.string.general_something_went_wrong),
        subtitle = stringResource(R.string.general_network_alien),
        extraContent = {}
    )
}
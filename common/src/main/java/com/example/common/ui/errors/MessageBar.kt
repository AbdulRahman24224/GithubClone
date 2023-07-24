package com.example.common.ui.errors

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.common.ui.AppColors
import com.example.common.ui.sH

@Composable
fun MessageBar(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
    contentModifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp)
        .padding(vertical = 10.dp),
    message: String = "",
    backgroundColor: Color = AppColors.Error,
    icon: Int = com.example.githubclone.R.drawable.ic_warning,
    barShape: RoundedCornerShape = RoundedCornerShape(5.dp),
) {
    Surface(
        modifier = modifier,
        shape = barShape, color = backgroundColor
    ) {
        Row(
            modifier = contentModifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Icon(
                modifier =
                Modifier
                    .requiredSize(30.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = icon),
                contentDescription = "",
            )

            sH(10)
            Text(message, style = MaterialTheme.typography.bodyMedium.copy(color = AppColors.White))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MessageBarPreview() {
    MessageBar(
        message = "jkdfjlsdf jkdfjlsdf jkdfjlsdf jkdfjlsdf jkdfjlsdfjkdfjlsdf jkdfjlsdf jkdfjlsdf jkdfjlsdf jkdfjlsdf ",
        contentModifier =
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(vertical = 10.dp)
    )
}
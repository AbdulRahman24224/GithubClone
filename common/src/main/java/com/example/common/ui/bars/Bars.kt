package com.example.common.ui.bars

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.common.ui.errors.MessageBar

@Composable
private fun SnackBar(
    message:String,
    backGroundColor: Color,
    actions: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(
            visible = message.isNotBlank(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            MessageBar(
                modifier = Modifier.padding(10.dp),
                message = message,
                actions = actions,
                backgroundColor = backGroundColor,
            )
        }
    }

}
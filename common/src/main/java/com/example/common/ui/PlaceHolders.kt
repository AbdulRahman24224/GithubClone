package com.example.common.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer

fun Modifier.shimmerPlaceHolder(isVisible: Boolean) = composed {
    this.placeholder(
        visible = isVisible,
        highlight = PlaceholderHighlight.shimmer(AppColors.Grey100),
        color = AppColors.Grey200
    )
}
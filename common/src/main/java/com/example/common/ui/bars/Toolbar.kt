package com.example.common.ui.bars

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
  title: String,
  optionsIcon: @Composable() () -> Unit,
) {
  TopAppBar(
    title = { Text(title) },
    actions = { optionsIcon() }
  )
}
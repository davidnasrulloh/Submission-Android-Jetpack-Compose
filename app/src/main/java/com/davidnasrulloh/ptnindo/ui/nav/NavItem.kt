package com.davidnasrulloh.ptnindo.ui.nav

import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val title: String,
    val iconNav: ImageVector,
    val screen: Screen,
    val contentDescription: String
)
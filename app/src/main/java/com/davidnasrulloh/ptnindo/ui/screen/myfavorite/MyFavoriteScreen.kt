package com.davidnasrulloh.ptnindo.ui.screen.myfavorite

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.davidnasrulloh.ptnindo.ui.components.empty_state.EmptyFavoriteComponent
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme

@Composable
fun MyFavoriteScreen() {
}


@Composable
fun MyFavoriteContent(
    modifier: Modifier = Modifier
) {
//    Scaffold(
//        topBar = {
//            TopBarContent()
//        },
//        modifier = modifier
//    ) {
//        if(){
//
//        } else {
//            EmptyFavoriteComponent()
//        }
//    }
}

@Composable
fun TopBarContent(
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text("My Favorit") },
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier,
    )
}


@Composable
fun MyFavoriteScreenPreview() {
    PtnindoTheme() {
        MyFavoriteContent()
    }
}
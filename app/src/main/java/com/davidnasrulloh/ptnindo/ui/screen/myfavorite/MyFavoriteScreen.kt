package com.davidnasrulloh.ptnindo.ui.screen.myfavorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davidnasrulloh.ptnindo.di.Injection
import com.davidnasrulloh.ptnindo.model.Univ
import com.davidnasrulloh.ptnindo.ui.components.empty_state.EmptyFavoriteComponent
import com.davidnasrulloh.ptnindo.ui.screen.ViewModelFactory
import com.davidnasrulloh.ptnindo.ui.screen.beranda.ListUniv
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme
import com.davidnasrulloh.ptnindo.ui.utils.UiState

@Composable
fun MyFavoriteScreen(
    navigateToDetailUniv: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyFavoriteViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUnivRepository())
    )
) {
    viewModel.uiStateFavorite.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteUniv()
            }

            is UiState.Success -> {
                MyFavoriteContent(
                    listUniv = uiState.data,
                    navigateToDetailUniv = navigateToDetailUniv,
                    onFavIconClicked = { id, newState ->
                        viewModel.updateUnivData(id, newState)
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}


@Composable
fun MyFavoriteContent(
    listUniv: List<Univ>,
    navigateToDetailUniv: (Int) -> Unit,
    onFavIconClicked: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopBarContent()
        },
        modifier = modifier
    ) { inPadding ->
        Column(
            modifier = modifier
                .padding(inPadding)
        ) {
            if (listUniv.isNotEmpty()) {
                ListUniv(
                    listUniv = listUniv,
                    onFavIconClicked = onFavIconClicked,
                    navigateToDetailUniv = navigateToDetailUniv,
                )
            } else {
                EmptyFavoriteComponent()
            }
        }
    }
}


@Composable
fun TopBarContent(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = "My Favorit",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White
            )
        },
        elevation = 1.dp,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = modifier,
    )
}


@Composable
fun MyFavoriteScreenPreview() {
    PtnindoTheme() {
        MyFavoriteContent(
            listUniv = emptyList(),
            navigateToDetailUniv = {},
            onFavIconClicked = { _, _ -> }
        )
    }
}
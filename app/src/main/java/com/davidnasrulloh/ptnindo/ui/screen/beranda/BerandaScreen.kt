package com.davidnasrulloh.ptnindo.ui.screen.beranda

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.davidnasrulloh.ptnindo.di.Injection
import com.davidnasrulloh.ptnindo.model.Univ
import com.davidnasrulloh.ptnindo.ui.components.EmptyContentComponent
import com.davidnasrulloh.ptnindo.ui.components.UnivItemCard
import com.davidnasrulloh.ptnindo.ui.screen.ViewModelFactory
import com.davidnasrulloh.ptnindo.ui.utils.UiState

@Composable
fun BerandaScreen(
    navigateToDetailUniv: (Int) -> Unit,
    viewModel: BerandaViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUnivRepository())
    )
) {
    viewModel.uiStateBeranda.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllUnivData()
            }

            is UiState.Success -> {
                BerandaContent(
                    listUniv = uiState.data,
                    onFavIconClicked = { id, newState ->
                        viewModel.updateUnivData(id, newState)
                    },
                    navigateToDetailUniv = navigateToDetailUniv,
                )
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun BerandaContent(
    listUniv: List<Univ>,
    onFavIconClicked: (id: Int, newState: Boolean) -> Unit,
    navigateToDetailUniv: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Univ PTN Indonesia",
                        style = MaterialTheme.typography.h5.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White
                    )
                },
            )
        },
        modifier = modifier,
        content = { inPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(inPadding)
            ) {
                if (listUniv.isNotEmpty()) {
                    ListUniv(
                        listUniv = listUniv,
                        onFavIconClicked = onFavIconClicked,
                        navigateToDetailUniv = navigateToDetailUniv
                    )
                } else {
                    EmptyContentComponent(
                        modifier = Modifier
                            .testTag("empty_content")
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListUniv(
    listUniv: List<Univ>,
    onFavIconClicked: (id: Int, newState: Boolean) -> Unit,
    navigateToDetailUniv: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .testTag("lazy_list")
    ) {
        items(
            listUniv, key = { it.id }
        ) { univ ->
            UnivItemCard(
                id = univ.id,
                name = univ.name,
                location = univ.location,
                imageUrl = univ.imgUrl,
                isFavorite = univ.isFavorite,
                onFavIconClick = onFavIconClicked,
                modifier = Modifier
                    .animateItemPlacement(tween(durationMillis = 200))
                    .clickable { navigateToDetailUniv(univ.id) }
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BerandaScreenPreview() {
    PtnindoTheme() {

    }
}


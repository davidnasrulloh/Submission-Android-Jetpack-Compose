package com.davidnasrulloh.ptnindo.ui.screen.detailuniv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.davidnasrulloh.ptnindo.di.Injection
import com.davidnasrulloh.ptnindo.ui.screen.ViewModelFactory
import com.davidnasrulloh.ptnindo.ui.screen.beranda.BerandaViewModel
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme
import com.davidnasrulloh.ptnindo.ui.utils.UiState

@Composable
fun DetailUnivScreen (
    univId: Int,
    navigateBack: () -> Unit,
    viewModel: DetailUnivViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideUnivRepository())
    )
) {
    viewModel.uiStateDetailUniv.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getUnivDataById(univId)
            }
            is UiState.Success -> {
                val item = uiState.data
                DetailContentUniv(
                    id = item.id,
                    name = item.name,
                    description = item.description,
                    location = item.location,
                    imgUrl = item.imgUrl,
                    isFavorite = item.isFavorite,
                    navigateBack = navigateBack ,
                    onFavIconClicked = { id, new ->
                        viewModel.updateUnivData(id, new)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}


@Composable
fun DetailContentUniv(
    id: Int,
    name: String,
    description: String,
    location: String,
    imgUrl: String,
    isFavorite: Boolean,
    navigateBack: () -> Unit,
    onFavIconClicked: (id: Int, state: Boolean) -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = navigateBack,
                modifier = Modifier
                    .padding(12.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.Blue),

            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack ,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = { onFavIconClicked(id, isFavorite) },
                modifier = Modifier
                    .padding(12.dp)
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.White)
            ) {
                Icon(
                    imageVector = if(!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite ,
                    contentDescription = if (!isFavorite) "Tambahkan Ke Favorite" else "Hapus dari favorite",
                    tint = if(!isFavorite) Color.Black else Color.Red
                )
            }
        }
        AsyncImage(
            model = imgUrl ,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
        )
        Spacer(modifier = modifier.height(12.dp))
        Column(
            modifier = modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onFavIconClicked(id, isFavorite) },
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(CircleShape)
                        .size(40.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "location",
                        tint = if(!isFavorite) Color.Black else Color.Red
                    )
                }
                Text(
                    text = location,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                )
                Divider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailUnivScreenPreview(){
    PtnindoTheme {
        DetailContentUniv(
            id = 1,
            name = "Universitas Trunojoyo Madura",
            description = "Panjang bet lah",
            location = "Bangkalan",
            imgUrl = "",
            isFavorite = false,
            navigateBack = {},
            onFavIconClicked = {_, _ ->{}},
        )
    }
}
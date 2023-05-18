package com.davidnasrulloh.ptnindo.ui.components.empty_state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.davidnasrulloh.ptnindo.R
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme

@Composable
fun EmptyFavoriteComponent() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.favorite_empty),
                contentDescription = stringResource(R.string.favorite_empty),
                modifier = Modifier
                    .size(140.dp)
                    .scale(1.5f)
            )
            Text(
                text = "Favorite Empty",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .padding(top = 60.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun EmptyStateComponentPreview() {
    PtnindoTheme() {
        EmptyFavoriteComponent()
    }
}
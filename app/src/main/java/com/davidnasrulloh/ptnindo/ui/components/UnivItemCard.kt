package com.davidnasrulloh.ptnindo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme

@Composable
fun UnivItemCard(
    id: Int,
    name: String,
    location: String,
    imageUrl: String,
    isFavorite: Boolean,
    onFavIconClick: (id: Int, newState: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp)),
        elevation = 4.dp,
    ){
        Column(
            modifier = Modifier
                .padding(2.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column() {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = location,
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = modifier.padding(top = 4.dp)
                    )
                }
                Icon(
                    imageVector = if (isFavorite) Icons.Rounded.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    tint = if (!isFavorite) Color.Black else Color.Red,
                    modifier = Modifier
                        .clickable { onFavIconClick(id, !isFavorite) }
                        .padding(2.dp)
                        .size(24.dp)
                        .testTag("fav_button")
                )
            }
            Divider(color = Color.Black, thickness = 2.dp)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun UnivItemCardPreview() {
    PtnindoTheme() {
        UnivItemCard(
            id = 0,
            name = "Universitas Trunojoyo Madura",
            location = "Bangkalan, Jawa Timur",
            imageUrl = "",
            isFavorite = false,
            onFavIconClick = {_,_ -> {}},
        )
    }
}
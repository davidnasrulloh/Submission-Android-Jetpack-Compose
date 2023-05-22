package com.davidnasrulloh.ptnindo.ui.screen.aboutme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.davidnasrulloh.ptnindo.R
import com.davidnasrulloh.ptnindo.ui.theme.PtnindoTheme

@Composable
fun AboutMeScreen(
    navigateBackButton: () -> Unit,
    modifier: Modifier = Modifier
        .testTag("about_page")
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.david),
                contentDescription = stringResource(R.string.david_profile),
                modifier = Modifier
                    .size(200.dp)
                    .scale(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.TopStart)
            ) {
                IconButton(
                    onClick = navigateBackButton,
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.Blue)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = "back",
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = stringResource(R.string.about_title),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Blue,
                    modifier = modifier
                        .fillMaxWidth()
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(MaterialTheme.colors.secondary)
                    .padding(top = 20.dp, bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.nama),
                    color = Color.Black,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = modifier
                        .padding(4.dp)
                )
                Text(
                    text = stringResource(R.string.email),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = modifier
                        .padding(4.dp)
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun AboutMeScreenPreview(){
    PtnindoTheme() {
        AboutMeScreen(
            navigateBackButton = {}
        )
    }
}
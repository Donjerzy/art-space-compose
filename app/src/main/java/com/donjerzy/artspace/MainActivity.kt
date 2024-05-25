package com.donjerzy.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.donjerzy.artspace.ui.theme.ArtspaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtspaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomePage()
                }
            }
        }
    }
}

data class Art (
    val index: Int,
    val image: Int,
    val title: String,
    val description: String
)

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    val artPieces = listOf(Art(
        index = 0,
        image = R.drawable.lion,
        title = "Lion",
        description = "King of the jungle"
    ), Art(
        index = 1,
        image = R.drawable.shark,
        title = "Shark",
        description = "Prince of the ocean"
    ), Art(
        index = 2,
        image = R.drawable.bear,
        title = "Bear",
        description = "Kindly bear with it"
    ))

    var currentPiece by remember {
       mutableStateOf(artPieces[0])
    }


    val previousClicked : () -> Unit = {
        when(currentPiece.index) {
            0 -> currentPiece = artPieces[2]
            1 -> currentPiece = artPieces[0]
            2 -> currentPiece = artPieces[1]
        }
    }

    val nextClicked : () -> Unit =  {
        when(currentPiece.index) {
            0 -> currentPiece = artPieces[1]
            1 -> currentPiece = artPieces[2]
            2 -> currentPiece = artPieces[0]
        }
    }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Column(modifier = Modifier
        .fillMaxWidth()
        .height(screenHeight)
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
        .height(IntrinsicSize.Max)
    ) {

        Row(
            modifier = Modifier.height(400.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PictureCard(img = currentPiece.image)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(modifier = Modifier.height(200.dp), verticalAlignment = Alignment.CenterVertically) {
            PictureInfo(title = currentPiece.title, description = currentPiece.description)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxHeight(1f),
            verticalAlignment = Alignment.Bottom, horizontalArrangement = Arrangement.Center
        ) {
            NavButton(
                label = "Previous", modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .width(120.dp),
                clicked = previousClicked
            )
            Spacer(modifier = Modifier.weight(2f))
            NavButton(
                label = "Next", modifier = Modifier
                    .weight(1f)
                    .height(40.dp)
                    .width(120.dp),
                clicked = nextClicked
            )
        }
    }

}


@Composable
fun PictureCard(modifier: Modifier = Modifier, @DrawableRes img: Int){
    Card {
        Image(painter = painterResource(id = img), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.height(400.dp))
    }
}


@Composable
fun PictureInfo(modifier: Modifier = Modifier, title: String, description: String) {
    Column(modifier = modifier) {
        Text(text = title, fontSize = 40.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = description, fontSize = 20.sp,)
    }
}


@Composable
fun NavButton(label: String, modifier: Modifier = Modifier, clicked: ()-> Unit) {
    Button(onClick = clicked, modifier = modifier) {
        Text(text = label)
    }
}

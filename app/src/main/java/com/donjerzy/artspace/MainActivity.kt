package com.donjerzy.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
                    HomePage(modifier = Modifier.fillMaxSize())
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
    var currentPiece: Art = artPieces[0]

    val buttonPressed: (String, Int) -> Unit = { action, currIndex ->
        when (action) {
            "next" -> {
                when(currIndex) {
                    0 -> currentPiece = artPieces[1]
                    1 -> currentPiece = artPieces[2]
                    2 -> currentPiece = artPieces[0]
                }
            }
            "previous" -> {
                when(currIndex) {
                    0 -> currentPiece = artPieces[2]
                    1 -> currentPiece = artPieces[0]
                    2 -> currentPiece = artPieces[1]
                }
            }
        }
    }

    Column(modifier = modifier) {
        Row {
            PictureCard(img = currentPiece.image)
        }
        Row {
            PictureInfo(title = currentPiece.title, description = currentPiece.description)
        }
        Row {
            NavButton(label = "Previous")
            NavButton(label = "Next")
        }

    }
}


@Composable
fun PictureCard(modifier: Modifier = Modifier, @DrawableRes img: Int){
    Card {
        Image(painter = painterResource(id = img), contentDescription = null)
    }
}


@Composable
fun PictureInfo(modifier: Modifier = Modifier, title: String, description: String) {
    Column {
        Text(text = title)
        Text(text = description )
    }
}


@Composable
fun NavButton(label: String, modifier: Modifier = Modifier) {
    Button(onClick = { /*TODO*/ }) {
        Text(text = label)
    }
}

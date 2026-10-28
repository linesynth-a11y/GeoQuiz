package com.sample.geoquiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.sample.geoquiz.ui.theme.GeoQuizTheme

data class Question(val text: String, val answer: Boolean)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeoQuizTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GeoQuizScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun GeoQuizScreen() {

}


@Preview(showBackground = true)
@Composable
fun GeoQuizPreview() {
    GeoQuizTheme {
        GeoQuizScreen()
    }
}
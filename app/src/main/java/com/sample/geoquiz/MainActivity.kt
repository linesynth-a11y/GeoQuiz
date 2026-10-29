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
    val context = LocalContext.current

    val questions = listOf(
        Question("Canberra is the capital of Australia.", true),
        Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        Question("The source of the Nile River is in Egypt.", false),
        Question("The Amazon River is the longest river in the Americas.", true),
        Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var isAnswered by remember { mutableStateOf(false) }

    var showResultDialog by remember { mutableStateOf(false) }
    var finalScore by remember { mutableStateOf(0) }


    val currentQuestion = questions[currentIndex]
    val isLastQuestion = currentIndex == questions.lastIndex

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("GeoQuiz", color = Color.White, fontSize = 22.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Magenta
                )
            )
        }
    ) {
            paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = currentQuestion.text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (!isAnswered) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            val updatedScore = if (currentQuestion.answer) score + 1 else score
                            score = updatedScore
                            isAnswered = true

                            if (isLastQuestion) {
                                finalScore = updatedScore
                                showResultDialog = true
                            }
                        },
                        modifier = Modifier.weight(1f).padding(end = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                    ) {
                        Text("TRUE", color = Color.White)
                    }

                    Button(
                        onClick = {
                            val updatedScore = if (!currentQuestion.answer) score + 1 else score
                            score = updatedScore
                            isAnswered = true

                            if (isLastQuestion) {
                                finalScore = updatedScore
                                showResultDialog = true
                            }
                        },
                        modifier = Modifier.weight(1f).padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                    ) {
                        Text("FALSE", color = Color.White)
                    }
                }
            } else if (!isLastQuestion) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            currentIndex++
                            isAnswered = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                    ) {
                        Text("NEXT  >", color = Color.White)
                    }
                }
            } else {
                Text("вы ответили на все вопросы", color = Color.Gray)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }

    if (showResultDialog) {
        AlertDialog(
            onDismissRequest = { showResultDialog = false },
            title = {
                Text(
                    text = "тест завершен",
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = "правильных ответов: $finalScore из ${questions.size}",
                    fontSize = 18.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showResultDialog = false
                        currentIndex = 0
                        score = 0
                        isAnswered = false
                    }
                ) {
                    Text("начать заново")
                }
            }
        )
    }
}

fun checkEndGame(isLastQuestion: Boolean, score: Int, context: android.content.Context) {
    if (isLastQuestion) {
        val message = "тест завершен. \nправильных ответов: $score из 6"
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}


@Preview(showBackground = true)
@Composable
fun GeoQuizPreview() {
    GeoQuizTheme {
        GeoQuizScreen()
    }
}
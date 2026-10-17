package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.shape.RoundedCornerShape
import kotlin.random.Random
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Lemonade(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
    var etape by remember { mutableIntStateOf(1) }
    var pressionsRestantes by remember { mutableIntStateOf(0) }

    val instruction = when (etape) {
        1 -> stringResource(R.string.image_1_text)
        2 -> stringResource(R.string.image_2_text)
        3 -> stringResource(R.string.image_3_text)
        else -> stringResource(R.string.image_4_text)
    }

    val image = when (etape) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = instruction,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                when (etape) {
                    1 -> {
                        pressionsRestantes = Random.nextInt(2, 5)
                        etape = 2
                    }
                    2 -> {
                        pressionsRestantes--
                        if (pressionsRestantes == 0) {
                            etape = 3
                        }
                    }
                    3 -> etape = 4
                    4 -> etape = 1
                }
            },
            modifier = Modifier.size(220.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, Color(0xFFB7D8A8)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4F9EF)
            )
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = instruction,
                modifier = Modifier.size(160.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (etape == 2) {
            Text(
                text = "Pressions restantes : $pressionsRestantes",
                fontSize = 14.sp
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        Lemonade()
    }
}
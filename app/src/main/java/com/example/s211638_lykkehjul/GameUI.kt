package com.example.s211638_lykkehjul

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel


class GameUI {
}


@Composable
fun GamePage(state: WheelUiState, spinFun: ()->Unit,
             guessfun: (letter: String)->Unit,
startfun: ()->Unit){
    Surface {
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Text(text = "Lykkehjul", fontSize = 40.sp)
            if (state.isRunning) {
                Text(text = state.lives.toString())
                Text(text = state.playerBalance.toString())
                val letter = remember {
                    mutableStateOf("")
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = letter.value, onValueChange = { letter.value = it },
                        singleLine = true
                    )
                    var enabled = false
                    if (letter.value.length == 1) {
                        if (!state.spinnable) {
                            enabled = true
                        }
                    }
                    Button(onClick = { guessfun(letter.value) }, enabled = enabled) {
                        Text("Gæt")
                    }
                }
                Text(state.wordDisplayed, fontSize = 40.sp)
                var enabled2: Boolean = false
                if (state.spinnable) {
                    enabled2 = true
                }
                Button(onClick = { spinFun() }, enabled = enabled2) {
                    Text(text = "Drej hjulet")
                }
                Text(text = "£ " + state.BalanceObtained.toString())
            }
            else {
                Button(onClick = { startfun()}) {

                }
            }
        }

    }

}

@Composable
fun GuessPlace() {

}

@Preview
@Composable
fun gameprev(){
    GamePage(WheelUiState(), {}, {}, {})
}
package com.example.s211638_lykkehjul

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.s211638_lykkehjul.ui.theme.S211638_LykkehjulTheme

class MainActivity : ComponentActivity() {
    private val viewModel = WheelviewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            S211638_LykkehjulTheme {
                WheelApp(viewModel)
            }
        }
    }
}

@Composable
fun WheelApp(viewModel: WheelviewModel){
    S211638_LykkehjulTheme() {
       val state = viewModel.uiState.value
                GamePage(state = state,
                    {viewModel.spin()},
                    {viewModel.checkLetter(it)},
                    {viewModel.startgame()})

    }

}
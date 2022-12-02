package com.example.s211638_lykkehjul

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.lang.StringBuilder

data class WheelUiState(
    val isRunning: Boolean = false,
val WheelResult: String = "hi",
    val BalanceObtained: Int = 0,
    val wordDrawn: String = "",
val wordDisplayed: String = "",
    val spinnable: Boolean = true,
    val lives: Int = 5,
val playerBalance: Int = 0,
    val category: String = ""
)
class WheelviewModel() : ViewModel() {
    private val _uiState = mutableStateOf(WheelUiState())
    val uiState: State<WheelUiState> = _uiState

    var lives: Int = 5


            fun checkIfLetterIsFound(){
                val letterNotFound: Boolean = false
            if (letterNotFound){

            }
            }
    fun spin(){
        if(uiState.value.wordDrawn.equals("")){
            draw()
        }
       val  wheelresult=(1..5).random()
        val resulttext=when(wheelresult){
            1->"1"
            2->"2"
            3->"3"
            4->"4"
            5 -> "5"
            else-> "0"
        }
        if (wheelresult==5){
            balance=0
        }
System.out.println("inde i spin")
        _uiState.value = _uiState.value.copy(WheelResult = resulttext, BalanceObtained = wheelresult, spinnable = false )
        if(_uiState.value.WheelResult == resulttext){
            System.out.println("Opdateret")
        }

    }
    fun draw(){
        val random = (1..3).random()
        val wordDrawn = when(random){
            1->WordList.Capitals.get((0..2).random())
            2->WordList.drinks.get((0..2).random())
            3->WordList.carBrands.get((0..2).random())
            else-> "hejsa"
        }


        val wordDispalyedtemp: StringBuilder = StringBuilder()
        for(i in 1..wordDrawn.length){
            wordDispalyedtemp.append("?")
        }
        _uiState.value = _uiState.value.copy(wordDisplayed = wordDispalyedtemp.toString(), wordDrawn = wordDrawn, spinnable = true)
    }
    var balance = 0
    fun checkLetter(letter: String){
        var isLetterFound: Boolean = false
        var wordDisplayedtemp: StringBuilder = StringBuilder()
        System.out.println("inde i check")
        wordDisplayedtemp.append(uiState.value.wordDisplayed)
       for(i in 0..uiState.value.wordDrawn.length-1) {
           System.out.println("inde i loop")
           if(letter.get(0).equals(uiState.value.wordDrawn.get(i), ignoreCase = true)){
              wordDisplayedtemp.setCharAt(i, letter.get(0))
               balance=balance+uiState.value.BalanceObtained
               System.out.println("inde i if")
               isLetterFound=true
           }
       }
        if(isLetterFound==false){
            lives=lives-1
        }
_uiState.value = _uiState.value.copy(playerBalance = balance,wordDisplayed = wordDisplayedtemp.toString(), spinnable = true, lives = lives)
        checkStatus()
    }
    fun updateProgress(){
_uiState.value=_uiState.value.copy(wordDrawn = "",
    wordDisplayed = "",
    spinnable = true,
    isRunning = false,
BalanceObtained = 0,
    lives = 5,
    WheelResult = "",
    playerBalance = 0

)
    }
    fun checkStatus(){
if(uiState.value.wordDrawn.equals(uiState.value.wordDisplayed, ignoreCase = true) || lives==0){
    updateProgress()
}
    }
    fun startgame(){
        _uiState.value = uiState.value.copy(isRunning = true, lives=5, playerBalance = 0)
    }
}
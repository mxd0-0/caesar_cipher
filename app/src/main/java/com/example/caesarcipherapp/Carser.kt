package com.example.caesarcipherapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class Carser : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun Encryption(
        cipherText: String,
        rotationKey: Int
    ): String {
        var plainText = ""
        for (character in cipherText) {
            val isLowerCase = character.isLowerCase()
            val upperChar = character.uppercaseChar()
            val characterValue = alphabet.indexOf(upperChar)
            if (characterValue != -1) {
                val shiftCharacter = (characterValue - rotationKey).mod(alphabet.length)
                val decryptedChar = alphabet[shiftCharacter]
                plainText += if (isLowerCase) decryptedChar.lowercaseChar() else decryptedChar
            } else {
                plainText += character
            }
        }
        return plainText
    }
}
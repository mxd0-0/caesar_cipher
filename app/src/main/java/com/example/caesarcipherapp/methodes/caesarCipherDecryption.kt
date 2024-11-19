package com.example.caesarcipherapp.methodes

private const val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun caesarCipherDecryption(
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
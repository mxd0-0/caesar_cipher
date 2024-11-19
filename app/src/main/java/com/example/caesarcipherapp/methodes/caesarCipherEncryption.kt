package com.example.caesarcipherapp.methodes
private const val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun caesarCipherEncryption(
    plainText: String,
    rotationKey: Int
): String {
    var cipherText = ""
    // to make the algorithm case-insensitive
    val plainTextCase = plainText.uppercase()
    for (character in plainTextCase) {
        // to find the numerical value of the character item in the plain text
        val characterValue = alphabet.indexOf(character)
        // to find the numerical value of the encrypted letter.
        val shiftCharacter = (characterValue + rotationKey) % alphabet.length
        // adding the string back to cipher text
        cipherText += alphabet[shiftCharacter]
    }
    return cipherText
}
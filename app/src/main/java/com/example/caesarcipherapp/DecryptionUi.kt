package com.example.caesarcipherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.caesarcipherapp.methodes.caesarCipherDecryption

@Composable
fun DecryptionUi(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    val errorMessage = "Please Enter Between 0 and 27"

    var decryptionKey by remember { mutableStateOf("") }
    val key =  decryptionKey.toIntOrNull() ?: 0
    var isKeyError by remember { mutableStateOf(false) }
    val result = caesarCipherDecryption(text, key)
    val showResult = remember { mutableStateOf(false) }

    val clipboardManager: ClipboardManager = LocalClipboardManager.current


    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (showResult.value && text.isNotEmpty() && key in 1..26) {
            Text(text = "Decrypted Text: $result",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.clickable {   clipboardManager.setText(AnnotatedString(result)) }
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text to Decrypt") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            ),
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = decryptionKey,
            onValueChange = {
                decryptionKey = it
                isKeyError = !isValidKey(it)
            },
            label = { Text("Enter number from 0 to 27") },
            isError = isKeyError,
            supportingText = { if (isKeyError) Text(errorMessage) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        )
        Button(
            onClick = {
                showResult.value = true
            }
        ) { Text("Encrypt") }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Preview
@Composable
private fun DecryptionUiPrev() {
    DecryptionUi()
}
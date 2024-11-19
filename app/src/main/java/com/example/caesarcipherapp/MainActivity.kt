// MainActivity.kt
package com.example.caesarcipherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.caesarcipherapp.ui.theme.CaesarCipherAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var bottomNavState by rememberSaveable {
                mutableStateOf(0)
            }
            CaesarCipherAppTheme {
                Scaffold(
                    topBar = {
                        if (bottomNavState == 0) {
                            CenterAlignedTopAppBar(
                                title = { Text("Encryption", color = MaterialTheme.colorScheme.onPrimary) },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        } else {
                            CenterAlignedTopAppBar(
                                title = { Text("Decryption", color = MaterialTheme.colorScheme.onPrimary) },
                                colors = TopAppBarDefaults.topAppBarColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    },
                    bottomBar = {
                        var selectedItem by remember { mutableIntStateOf(0) }
                        val items = listOf("Encryption", "Decryption")
                        val selectedIcons =
                            listOf(R.drawable.lock, R.drawable.unlock)

                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = selectedIcons[index]),
                                            contentDescription = null,
                                        )
                                    },
                                    selected = selectedItem == index,
                                    onClick = {
                                        selectedItem = index
                                        bottomNavState = index
                                    }
                                )
                            }
                        }
                    },
                    content = { PaddingValues ->
                        when (bottomNavState) {
                            0 -> EncryptionUi(modifier = Modifier.padding(PaddingValues))
                            1 -> DecryptionUi(modifier = Modifier.padding(PaddingValues))
                        }
                    }
                )
            }
        }
    }
}
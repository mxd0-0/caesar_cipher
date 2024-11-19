package com.example.caesarcipherapp.compoment

import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.caesarcipherapp.R

@Composable
fun CsBottomAppBar(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Encryption", "Decryption")
    val selectedIcons =
        listOf(R.drawable.lock, R.drawable.unlock)

    NavigationBar(
        modifier = modifier
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = selectedIcons[index]),
                        contentDescription = null,
                        // tint = if (selectedItem == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                    )
                },
                selected = selectedItem == index,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Preview
@Composable
private fun BottomAppBarPrev() {
    MaterialTheme {
        CsBottomAppBar()
    }
}
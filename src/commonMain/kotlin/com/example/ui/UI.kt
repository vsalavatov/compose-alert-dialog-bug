package com.example.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ui.expect.AlertDialog


@Composable
fun UI() {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        AlertDialog(
            modifier = Modifier.wrapContentSize(),
            onDismissRequest = { showDialog = false },
            title = { Text("alert dialog example") },
            buttons = {
                var text by remember { mutableStateOf("click me") }
                Button(onClick = { text = "$text!" }) { Text(text)}
            },
            content = {
                val scrollState = rememberScrollState()
                Column(modifier = Modifier
                    .verticalScroll(scrollState)
                ) {
                    repeat(100) {
                        Text("$it", modifier = Modifier.padding(2.dp))
                    }
                }
            }
        )
    }
    Button(onClick = {
        showDialog = !showDialog
    }) {
        Text("toggle dialog")
    }
}
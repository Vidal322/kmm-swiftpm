package org.example.pedro

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import pedro.composeapp.generated.resources.Res
import pedro.composeapp.generated.resources.compose_multiplatform

@Composable
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        var responseText by remember { mutableStateOf("No response yet") }

        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            AnimatedVisibility(showContent) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("From swift package: ${printHelloWorld()}")
                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        makeNetworkRequest(
                            onSuccess = { response ->
                                responseText = " Success: $response"
                            },
                            onFailure = { error ->
                                responseText = " Error: $error"
                            }
                        )
                    }) {
                        Text("Make Network Request")
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(responseText)
                }
            }
        }
    }
}

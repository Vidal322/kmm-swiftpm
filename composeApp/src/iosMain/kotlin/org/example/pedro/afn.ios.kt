@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)

package org.example.pedro

import platform.Foundation.*
import org.example.pedro.afnetworking.AFHTTPSessionManager

fun makeNetworkRequest(onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
    val manager = AFHTTPSessionManager()
    val url = "https://jsonplaceholder.typicode.com/todos/1"

    manager.GET(
        URLString = url,
        parameters = null,
        headers = null,
        progress = null,
        success = { _, responseObject ->
            onSuccess(responseObject.toString())
        },
        failure = { _, error ->
            onFailure(error?.localizedDescription ?: "Unknown error")
        }
    )
}

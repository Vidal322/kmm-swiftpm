package org.example.pedro
import org.example.pedro.helloworld.HelloWorldPrinter
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
fun printHelloWorld(): String {
    val helloWorldPrinter = HelloWorldPrinter()

    val result = helloWorldPrinter.printHelloWorld()

    return result

}
package com.example.kotlinspringapp.utils

import java.util.*
import javax.crypto.KeyGenerator

fun generateSecretKey(): String {
    val keyGen = KeyGenerator.getInstance("HmacSHA256")
    keyGen.init(256) // Use 256-bit key
    val secretKey = keyGen.generateKey()
    return Base64.getEncoder().encodeToString(secretKey.encoded)
}

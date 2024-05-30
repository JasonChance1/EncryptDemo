package com.example.encryptdemo

object Caesar {
    fun decrypt(encrypt: String, key: Int = 1) {
        val charArray = encrypt.toCharArray()
        val result = with(StringBuilder()) {
            charArray.forEach {
                append((it.code - key).toChar())
            }
            toString()
        }
        println("CaesarDecrypt:${result}")
    }

    fun encrypt(text: String, offset: Int = 1): String {
        val charArray = text.toCharArray()
        val result = with(StringBuilder()) {
            charArray.forEach {
                var cCode = it.code
                cCode += offset
                append(cCode.toChar())
            }
            toString()
        }
        println("Caesar:$result")
        return result
    }
}
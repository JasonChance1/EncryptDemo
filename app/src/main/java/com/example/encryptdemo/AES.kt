package com.example.encryptdemo

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object AES {
    fun encrypt(text:String,password:String):ByteArray{
        val adjustArray = password.toByteArray().copyOf(16)

        val cipher = Cipher.getInstance("AES")
        val key = SecretKeySpec(adjustArray, "AES")
        cipher.init(Cipher.ENCRYPT_MODE,key)
        val encrypt = cipher.doFinal(text.toByteArray())
        println("AES加密：${String(encrypt)}")
        return encrypt
    }


    fun decrypt(encrypt: ByteArray,password: String){
        val cipher = Cipher.getInstance("AES")
        val adjust = password.toByteArray().copyOf(16)
        val key = SecretKeySpec(adjust, "AES")
        cipher.init(Cipher.DECRYPT_MODE,key)
        val decrypt = cipher.doFinal(encrypt)
        println("解密：${String(decrypt)}")
    }
}
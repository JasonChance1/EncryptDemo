package com.example.encryptdemo

import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.security.Key
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * 公钥加密，私钥解密；私钥加密，公钥解密
 * Client:c-PrivateKey,c-PublicKey  Service: s-PrivateKey,s-PublicKey
 * cs生成各自的密钥对，cs交换公钥，c用自己的私钥加密，s用c的公钥解密;s用自己的私钥加密，c用s的公钥解密
 *
 * 最大只能加密117个字节
 * 一次最大只能解密128字节
 */
object RSA {
    private const val MAX_ENCRYPT_BLOCK_SIZE = 117 // 最大加密块大小
    private const val DECRYPT_BLOCK_SIZE = 128 // 解密块大小

    fun encrypt(input: String, privateKey: PrivateKey): ByteArray {
        val data = input.toByteArray()
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, privateKey)

        val bos = ByteArrayOutputStream()
        var offset = 0

        while (data.size - offset > 0) {
            val blockSize = if (data.size - offset > MAX_ENCRYPT_BLOCK_SIZE) MAX_ENCRYPT_BLOCK_SIZE else data.size - offset
            val encryptedBlock = cipher.doFinal(data, offset, blockSize)
            bos.write(encryptedBlock)
            offset += blockSize
        }

        return bos.toByteArray()
    }

    fun decrypt(encryptedData: ByteArray, publicKey: PublicKey): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, publicKey)

        val bos = ByteArrayOutputStream()
        var offset = 0

        while (encryptedData.size - offset > 0) {
            val blockSize = if (encryptedData.size - offset > DECRYPT_BLOCK_SIZE) DECRYPT_BLOCK_SIZE else encryptedData.size - offset
            try {
                val decryptedBlock = cipher.doFinal(encryptedData, offset, blockSize)
                bos.write(decryptedBlock)
                offset += blockSize
            } catch (e: Exception) {
                println("解密块时出错，偏移量：$offset，块大小：$blockSize，总大小：${encryptedData.size}，错误：${e.message}")
                throw e
            }
        }

        return String(bos.toByteArray())
    }
}

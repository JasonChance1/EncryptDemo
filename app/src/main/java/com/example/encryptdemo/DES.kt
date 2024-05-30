package com.example.encryptdemo

import java.security.Key
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec
import javax.crypto.spec.IvParameterSpec

object DES {
    fun encrypt(text: String, password: String) :ByteArray{
        // 检查并调整密码长度
        val passwordBytes = password.toByteArray()
        val adjustedPassword =  passwordBytes.copyOf(24)

        // 获取Cipher实例
        val cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding") // 算法/模式/填充

        // 生成密钥
        val kf = SecretKeyFactory.getInstance("DESede")
        val key: Key = kf.generateSecret(DESedeKeySpec(adjustedPassword))

        // 初始化Cipher
        cipher.init(Cipher.ENCRYPT_MODE, key)

        // 执行加密操作
        val encrypt = cipher.doFinal(text.toByteArray())
        println("DES加密：${encrypt.joinToString(",")}")
        return encrypt
    }

    fun decrypt(text: ByteArray, password: String) {
        // 检查并调整密码长度
        val passwordBytes = password.toByteArray()
        val adjustedPassword = passwordBytes.copyOf(24)

        // 获取Cipher实例，工作模式：CBC(Cipher Blocking Chain),后面的块加密需要依赖前一块，更安全;ECB(Electronic codebook),分块独立加密，相同明文得到相同的加密文本。
        val cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding") // 算法/模式/填充

        // 生成密钥
        val kf = SecretKeyFactory.getInstance("DESede")
        val key: Key = kf.generateSecret(DESedeKeySpec(adjustedPassword))

        // 提取 IV（初始化向量）
        val iv = text.copyOfRange(0, 8)// CBC模式才需要此参数，
        val ivSpec = IvParameterSpec(iv)

        // 提取实际加密数据
        val encryptedText = text.copyOfRange(8, text.size)

        // 初始化Cipher
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec)

        // 执行解密操作
        val decrypt = cipher.doFinal(encryptedText)
        println("DES解密：${String(decrypt)}")
    }
}
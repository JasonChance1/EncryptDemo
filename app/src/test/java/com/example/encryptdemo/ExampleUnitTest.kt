package com.example.encryptdemo

import org.junit.Test

import java.security.Key
import java.security.KeyFactory
import java.security.KeyPairGenerator
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.DESedeKeySpec
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @OptIn(ExperimentalEncodingApi::class)
    @Test
    fun addition_isCorrect() {

        // 生成密钥对
//        val generator = KeyPairGenerator.getInstance("RSA")
//        val keyPair = generator.genKeyPair()
//        val privateKey = keyPair.private
//        val publicKey = keyPair.public
//        println("私钥：" + Base64.encode(privateKey.encoded))
//        println("公钥" + Base64.encode(publicKey.encoded))
//        val encrypt = RSA.encrypt(s,privateKey)
//        RSA.decrypt(encrypt,publicKey)
        val generator = KeyPairGenerator.getInstance("RSA")
        generator.initialize(1024) // 确保密钥大小为1024位
        val keyPair = generator.genKeyPair()
        val privateKeyStr = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJf0D+n9ORqol2WbT1TnHnLWox2NTSgiGQ2YYd/6usD3aIdTEsQdaP+GWRduqT0FIAwL/K47JiJ2SQ9Ta4D52IxaGV6Q275biRTI/qf7Rby3obHyNcmE9RrfX90+p4tGImtf2BeZ188reOs4VSht7gFnSd+6k1/kb95pbnJO0h7ZAgMBAAECgYAJkG4T/0FB4UyuM4lZwbZT6hiYOpWZqhPDnEzEyUZJ8QieQ+dRerxqIO2ooOl8Er+CopqD6Ossjh9pHIoOsysjCWCM61bq9JBbJ9WQ6bUqMJMMiIgNUxlEiRG++QFDKkSziOItPFbRHU3RsWcSIElPXgIyzUEkl4GsfxRZi/vX0QJBAK5oNTW4/hBfUWG6R6kGyJTo+U385IWxcFKM2rKOJrt2LuzNplMYH8i8wQZZq/RiPGf9gN6df/XcBlfGMMlj+LUCQQDfCrJUoSZ6P3CaTFXwrouEg6cauL+QspdaOjbRWCAeMvtfojw+nZXu7x3ku0hiPwWlBu++HNu7y6Ll+eOKbtgVAkB9exxNervQq8OxvISyZ98IxFYadkk0p2VY/16Z+QyE85LvbUatoj27/5IhL4M4YTV03D9L66WVDqjBniabvwHBAkBazoYQQpPhzZZQm6eZl9ahmuAb1v/XL37Dc1QCIzjgUq1ZIDoa3BnA74ViLIWpyjLIH2zYH/OuH5gwvW5QXBSJAkBWghT9O+wQsCrwRR/lyGWtL2iNR5Difcg0VBlScP0RWMNKF/o7nZeJQpvcQ7V8V5ko6RgtnH3pGa5UwianqEKu"
        val publicKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCX9A/p/TkaqJdlm09U5x5y1qMdjU0oIhkNmGHf+rrA92iHUxLEHWj/hlkXbqk9BSAMC/yuOyYidkkPU2uA+diMWhlekNu+W4kUyP6n+0W8t6Gx8jXJhPUa31/dPqeLRiJrX9gXmdfPK3jrOFUobe4BZ0nfupNf5G/eaW5yTtIe2QIDAQAB"
        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateKeyStr)))
        val publicKey = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(Base64.decode(publicKeyStr)))
//        val privateKey = KeyFactory.getInstance("RSA").generatePrivate(PKCS8EncodedKeySpec(Base64.decode(privateKeyStr)))
//        val publicKey = keyPair.public

        println("私钥：" + Base64.encode(privateKey.encoded))
        println("公钥：" + Base64.encode(publicKey.encoded))

        val s = "这是一个需要加密的字符串"
        val encrypt = RSA.encrypt(s, privateKey)
        val decrypted = RSA.decrypt(encrypt, publicKey)

        println("加密后的字符串：" + String(encrypt))
        println("解密后的字符串：$decrypted")
    }
    val s = "这是一个需要加密的字符串"

    @Test
    fun md5(){
        MessageDigestUtil.md5(s)
    }
    @Test
    fun sh1() {
        MessageDigestUtil.sh1(s)
    }

    @Test
    fun sha256() {
        MessageDigestUtil.sha256(s)
    }

    private fun toASCII(s:String) {
        val charArray = s.toCharArray()
        val result = with(StringBuffer()) {
            for (c in charArray) {
                append("${c.code} ")
            }
            toString()
        }

        println("ASCII:${result}")
    }
}
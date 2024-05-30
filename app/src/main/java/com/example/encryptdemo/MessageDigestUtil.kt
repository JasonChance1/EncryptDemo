package com.example.encryptdemo

import java.security.MessageDigest

object MessageDigestUtil {
    /**
     * 无论数据有多大，md5 加密后都是16字节
     */
    fun md5(input: String): String {
        val sb = StringBuffer()
        val md = MessageDigest.getInstance("MD5")
        val s = md.digest(input.toByteArray()) // 得到16字节内容
        println("加密后大小：${s.size}")
//         转16进制 变为32位
        s.forEach {
            val hex = it.toInt() and (0xff)
            val hexStr = Integer.toHexString(hex)
            if(hexStr.length==1){
                sb.append("0").append(hexStr)
            }else sb.append(hexStr)
        }
//        println("${md.digest().joinToString("") { "%02x".format(it) }}")
        println("转16进制：${toHex(s)}")
        println("sb转16进制：${sb}")
        return sb.toString()
    }

    /**
     * 加密后20字节，转16进制后40字节
     */
    fun sh1(input:String):String{
        val md = MessageDigest.getInstance("SHA1")
        val result = toHex(md.digest(input.toByteArray()))
        println("sha1：${result}")
        return result
    }

    /**
     * 加密后32字节，转16进制后64字节
     */
    fun sha256(input: String):String{
        val md = MessageDigest.getInstance("SHA256")
        val result = toHex(md.digest(input.toByteArray()))
        println("sha256：${result}")
        return result
    }
    private fun toHex(byteArray: ByteArray):String{
        return with(StringBuilder()){
            byteArray.forEach {
                val hex = it.toInt() and (0xff)
                val hexStr = Integer.toHexString(hex)
                if(hexStr.length==0) append("0").append(hexStr) else append(hexStr)
            }

            toString()
        }
    }
}
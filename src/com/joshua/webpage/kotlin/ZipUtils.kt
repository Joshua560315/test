package com.joshua.webpage.kotlin

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * Created by linzl on 17-1-22.
 */
class ZipUtil {
    companion object
}

fun ZipUtil.Companion.zip(filePath:String,outPath:String) {
    try {
        val file = File(filePath)
        val zipFile = File(outPath)
        val zipOut = ZipOutputStream(FileOutputStream(zipFile))
        recursionZip(zipOut,file,file.name + File.separator)
        zipOut.close()
    } catch (e:Exception) {
        e.printStackTrace()
    }
}

@Throws(Exception::class)
fun ZipUtil.Companion.recursionZip(zipOut: ZipOutputStream, file: File, baseDir: String) {
    if (file.isDirectory) {
        val files = file.listFiles()
        for (fileSec in files!!) {
            recursionZip(zipOut, fileSec, baseDir + file.name + File.separator)
        }
    } else {
        val buf = ByteArray(1024)
        val input = FileInputStream(file)
        zipOut.putNextEntry(ZipEntry(baseDir + file.name))
        var len: Int = input.read(buf)
        while (len !=-1) {
            zipOut.write(buf, 0, len)
            len = input.read(buf)
        }
        input.close()
    }
}
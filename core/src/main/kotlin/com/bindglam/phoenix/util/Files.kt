package com.bindglam.phoenix.util

import com.bindglam.phoenix.api.Phoenix
import java.io.File
import java.io.InputStream

fun InputStream.copyToFile(file: File) {
    file.outputStream().use { stream ->
        stream.write(readBytes())
    }
}

fun resource(path: String): InputStream? = Phoenix.instance().plugin().getResource(path)

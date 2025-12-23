package com.bindglam.phoenix.compatibility

interface Compatibility {
    val requiredPlugin: String

    fun start()

    fun end()
}
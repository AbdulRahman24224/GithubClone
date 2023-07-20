package com.example.network.utils


import org.json.JSONObject
import java.io.*


internal fun String.toException(): Throwable {
    try {
        var (errorCode, error, details) = Triple("", "", "")

        kotlin.runCatching { errorCode = JSONObject(this).getString("error_code") }
        kotlin.runCatching { error = JSONObject(this).getString("error") }
        kotlin.runCatching { details = JSONObject(this).getString("details") }

        return when (errorCode) {
            //"00000001" -> NationalIdAlreadyExistException(validationMessage = error)

            else -> Throwable(this)
        }
    } catch (e: Exception) {
        return Throwable(e)
    }
}


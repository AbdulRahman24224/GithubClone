package com.example.repositories

interface SettingsRepository {

    fun savePreferenceValue(key: String, value: String):Unit

    fun getPreferenceValue(key: String):String?
}
package com.example.datasources


interface PreferenceDataSource {

       fun saveValue(key: String, value: String)

       fun getValue(key: String): String?

}
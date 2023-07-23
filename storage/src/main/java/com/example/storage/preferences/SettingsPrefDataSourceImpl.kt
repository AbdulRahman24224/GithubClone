package com.example.storage.preferences

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.datasources.PreferenceDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : PreferenceDataSource {

    private val masterKey = MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    private val encryptedSharedPreferences = EncryptedSharedPreferences.create(
        context ,
        "settings_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )



    override  fun getValue(key: String): String? {
        return encryptedSharedPreferences.getString(key, null)
    }

    override  fun saveValue(key: String, value: String) {
        val editor = encryptedSharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }
}
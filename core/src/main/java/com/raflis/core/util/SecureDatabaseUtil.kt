package com.raflis.core.util

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import net.sqlcipher.database.SQLiteDatabase
import java.util.UUID

object SecureDatabaseUtil {

    private const val PREFS_NAME = "secure_prefs"
    private const val KEY_PASSPHRASE = "db_passphrase"

    fun getPassphrase(context: Context): ByteArray {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        val storedPassphrase = sharedPreferences.getString(KEY_PASSPHRASE, null)
        return if (storedPassphrase != null) {
            SQLiteDatabase.getBytes(storedPassphrase.toCharArray())
        } else {
            val newPassphrase = UUID.randomUUID().toString()
            sharedPreferences.edit { putString(KEY_PASSPHRASE, newPassphrase) }
            SQLiteDatabase.getBytes(newPassphrase.toCharArray())
        }
    }
}

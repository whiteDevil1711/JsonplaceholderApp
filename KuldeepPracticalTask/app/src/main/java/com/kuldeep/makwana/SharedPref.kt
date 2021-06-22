package com.kuldeep.makwana

import android.content.Context
import android.content.SharedPreferences

object SharedPref {

    private const val NAME = "BlueSalve"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    // list of app specific preferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    /**
     * SharedPreferences extension function, so we won't need to call edit() and apply()
     * ourselves on every SharedPreferences operation.
     */
    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    // End - user information
    fun save(key: String, value: Boolean) {
        preferences.edit { it.putBoolean(key, value).apply() }
    }

    fun save(key: String, value: Float) {
        preferences.edit { it.putFloat(key, value).apply() }
    }

    fun save(key: String, value: Int) {
        preferences.edit { it.putInt(key, value).apply() }
    }

    fun save(key: String, value: Long) {
        preferences.edit { it.putLong(key, value).apply() }
    }

    fun save(key: String, value: String) {
        preferences.edit { it.putString(key, value).apply() }
    }

    fun save(key: String, value: Set<String>) {
        preferences.edit { it.putStringSet(key, value).apply() }
    }

    fun getBoolean(key: String, defValue: Boolean): Boolean {
        return preferences.getBoolean(key, defValue)
    }

    fun getFloat(key: String, defValue: Float): Float {
        return try {
            preferences.getFloat(key, defValue)
        } catch (ex: ClassCastException) {
            preferences.getString(key, defValue.toString())!!.toFloat()
        }
    }

    fun getInt(key: String, defValue: Int): Int {
        return try {
            preferences.getInt(key, defValue)
        } catch (ex: ClassCastException) {
            preferences.getString(key, defValue.toString())!!.toInt()
        }
    }

    fun getLong(key: String, defValue: Long): Long {
        return try {
            preferences.getLong(key, defValue)
        } catch (ex: ClassCastException) {
            preferences.getString(key, defValue.toString())!!.toLong()
        }
    }

    fun getString(key: String, defValue: String): String? {
        return preferences.getString(key, defValue)
    }

    fun getStringSet(key: String, defValue: Set<String>): Set<String>? {
        return preferences.getStringSet(key, defValue)
    }

    fun getAll(): MutableMap<String, *>? {
        return preferences.all
    }

    fun remove(key: String) {
        preferences.edit { it.remove(key).apply() }
    }

    fun clear() {
        preferences.edit { it.clear().apply() }
    }
}

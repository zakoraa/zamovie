package com.raflis.core.util

import java.text.SimpleDateFormat
import java.util.Locale

object DateFormatter {
    fun formatDate(inputDate: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(inputDate ?: "")
            date?.let { outputFormat.format(it) } ?: "Unknown Date"
        } catch (e: Exception) {
            "Unknown Date"
        }
    }

    fun parseDate(inputDate: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(inputDate ?: "")
            date?.let { outputFormat.format(it) } ?: "Unknown Date"
        } catch (e: Exception) {
            "Unknown Date"
        }
    }

    fun extractYear(inputDate: String?): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(inputDate ?: "")
            date?.let { SimpleDateFormat("yyyy", Locale.getDefault()).format(it) } ?: "Unknown Year"
        } catch (e: Exception) {
            "Unknown Year"
        }
    }

}
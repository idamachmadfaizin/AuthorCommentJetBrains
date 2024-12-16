package com.idamachmadfaizin.authorcomment.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Formats the LocalDateTime using the provided pattern
 * @param pattern String representing the date time format pattern
 * @return Formatted date time string
 */
fun LocalDate.format(pattern: String): String {
    return try {
        DateTimeFormatter.ofPattern(pattern).format(this)
    } catch (e: Exception) {
        "Error: ${e.message}"
    }
}
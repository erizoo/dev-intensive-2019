package ru.skillbranch.devintensive.utils

import android.annotation.SuppressLint
import android.graphics.*
import androidx.annotation.ColorInt
import java.lang.Integer.min

object Utils {

    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? = fullName?.split(" ")

        val firstName = if (parts?.getOrNull(0) != null) {
            if (parts[0].isBlank()) null else parts[0]
        } else {
            null
        }
        val lastName = if (parts?.getOrNull(1) != null) {
            if (parts[1].isBlank()) null else parts[1]
        } else {
            null
        }

        return firstName to lastName
    }

    fun textBitmap(
            width: Int,
            height: Int,
            text: String = "",
            @ColorInt bgColor: Int = Color.BLACK,
            textSize: Int = (min(width, height) * 0.6f).toInt(),
            @ColorInt textColor: Int = Color.WHITE
    ): Bitmap {
        val bitmap: Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        canvas.drawColor(bgColor)

        if (text.isNotEmpty()) {
            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            paint.textSize = textSize.toFloat()
            paint.color = textColor
            paint.textAlign = Paint.Align.CENTER

            val textBounds = Rect()
            paint.getTextBounds(text, 0, text.length, textBounds)

            val backgroundBounds = RectF()
            backgroundBounds.set(0f, 0f, width.toFloat(), height.toFloat())

            val textBottom = backgroundBounds.centerY() - textBounds.exactCenterY()
            canvas.drawText(text, backgroundBounds.centerX(), textBottom, paint)
        }

        return bitmap
    }

    @SuppressLint("DefaultLocale")
    fun transliteration(payload: String, divider: String = " "): String {
        val list = payload.toLowerCase().split(" ")
        val sb = StringBuilder()
        for (ch in list[0].toCharArray()) {
            sb.append(cyrToLat(ch))
        }
        val firstName = sb.toString()
        sb.clear()
        for (ch in list[1].toCharArray()) {
            sb.append(cyrToLat(ch))
        }
        val lastName = sb.toString()
        return "${firstName.capitalize()}$divider${lastName.capitalize()}"
    }

    private fun cyrToLat(ch: Char): String? {
        return when (ch) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е' -> "e"
            'ё' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и' -> "i"
            'й' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ' -> ""
            'ы' -> "i"
            'ь' -> ""
            'э' -> "e"
            'ю' -> "yu"
            'я' -> "ya"
            else -> ch.toString()
        }
    }

    @SuppressLint("DefaultLocale")
    fun toInitials(firstName: String?, lastName: String?): String? {
        return if (firstName?.getOrNull(0) != null && lastName?.getOrNull(0) != null) {
            "${firstName[0]}${lastName[0]}".toUpperCase()
        } else if (firstName?.getOrNull(0) == null && lastName?.getOrNull(0) != null) {
            if (lastName.isNullOrBlank()) {
                null
            } else {
                "${lastName[0]}".toUpperCase()
            }
        } else if (firstName?.getOrNull(0) != null && lastName?.getOrNull(0) == null) {
            if (firstName.isNullOrBlank()) {
                null
            } else {
                "${firstName[0]}".toUpperCase()
            }
        } else {
            null
        }
    }


}
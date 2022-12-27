package com.iish.pulse.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Patterns
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.iish.pulse.data.source.responses.ErrorResponse
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Date


object Utils {

    const val mask = "xxx xxx xx xx"

    fun md5(password: String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(password.toByteArray(Charsets.UTF_8))).toString(16).padStart(32, '0')
    }

    fun <T> handler(response: Response<T>): Resource<T> {
        var errorResponse: ErrorResponse? = null
        if (response.isSuccessful) {
            response.body()?.let { it ->
                return Resource.Success(it)
            }
        } else {
            val gson = Gson()
            val type = object : TypeToken<ErrorResponse>() {}.type
            return try {
                errorResponse =
                    gson.fromJson(response.errorBody()?.charStream(), type)
                Resource.Error(response.message())
            } catch (e: JsonSyntaxException) {
                Resource.Error(response.message())
            }
        }
        return Resource.Error(response.message())
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

    fun mobileNumberFilter(text: AnnotatedString): TransformedText {
        // change the length
        val trimmed =
            if (text.text.length >= 10) text.text.substring(0..9) else text.text

        val annotatedString = AnnotatedString.Builder().run {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i == 2 || i == 5 || i == 7) {
                    append(" ")
                }
            }
            pushStyle(SpanStyle(color = Color.LightGray))
            append(mask.takeLast(mask.length - length))
            toAnnotatedString()
        }

        val phoneNumberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset + 1
                if (offset <= 7) return offset + 2
                if (offset <= 10) return offset + 3
                return 12
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset - 1
                if (offset <= 7) return offset - 2
                if (offset <= 10) return offset - 3
                return 9
            }
        }

        return TransformedText(annotatedString, phoneNumberOffsetTranslator)
    }

    fun Bitmap.toUri(inContext: Context): Uri? {
        val bytes = ByteArrayOutputStream()
        this.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            this,
            Date().toString(),
            null
        )
        return Uri.parse(path)
    }
}
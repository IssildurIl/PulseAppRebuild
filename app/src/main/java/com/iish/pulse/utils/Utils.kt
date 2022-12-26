package com.iish.pulse.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.iish.pulse.data.source.responses.ErrorResponse
import retrofit2.Response
import java.math.BigInteger
import java.security.MessageDigest


object Utils {

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

}
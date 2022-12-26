package com.iish.pulse.data.source.responses

data class UserAuth(
    val access_token: String,
    val userId: String,
    val revision: Int
)
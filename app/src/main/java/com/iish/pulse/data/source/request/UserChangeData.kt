package com.iish.pulse.data.source.request

data class UserChangeData(
    val name: String,
    val surname: String,
    val middleName: String,
    val phone:String,
    val mail: String,
    val publicName: String,
)
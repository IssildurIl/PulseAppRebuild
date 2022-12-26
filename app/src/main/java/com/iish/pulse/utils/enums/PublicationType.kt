package com.iish.pulse.utils.enums



object PublicationType{
    fun get(type: Enum):String {
        return this.javaClass.name.uppercase() + "." + type.name
    }

    fun convertName(type: Enum):String{
        return "PUBLICATIONTYPE.$type"
    }
    enum class Enum {
        Post,
        Event,
        MapObject,
        Organization
    }
}
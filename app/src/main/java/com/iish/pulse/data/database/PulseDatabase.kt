package com.iish.pulse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iish.pulse.data.features.user.UserDao
import com.iish.pulse.data.features.user.UserEntity

@Database(entities = [UserEntity::class], version = 2)
abstract class PulseDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
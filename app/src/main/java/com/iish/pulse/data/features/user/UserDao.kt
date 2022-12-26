package com.iish.pulse.data.features.user

import androidx.room.*

@Dao
interface UserDao {

    @Insert
    suspend fun insert(entity: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(entity: UserEntity)

    @Query("SELECT * from ${UserEntity.TABLE_DAILY_NAME}")
    suspend fun getUserData(): List<UserEntity>



}
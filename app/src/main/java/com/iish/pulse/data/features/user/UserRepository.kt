package com.iish.pulse.data.features.user

import javax.inject.Inject


class UserRepository @Inject constructor(
    private val userDao: UserDao
) {

    suspend fun fetchUserData(): List<UserEntity> = userDao.getUserData()

    suspend fun addOrUpdate(login: String, password: String, revision: Int? = 0) {
        val users = fetchUserData()
        val userForUpdate = users.firstOrNull{it.login == login}
        if (userForUpdate == null) {
            userDao.insert(
                UserEntity(
                    login = login, password = password, revision = revision
                )
            )
        } else {
            userDao.update(UserEntity(login = login, password = password, revision = revision))
        }
    }

}
package com.onlinestore.services

import com.onlinestore.db.dao.UsersDao
import com.onlinestore.models.User
import org.springframework.beans.factory.annotation.Autowired

class UsersService(
    @Autowired private val usersDao: UsersDao
) {

    fun updateUser(user: User) = usersDao.updateUser(user)
}
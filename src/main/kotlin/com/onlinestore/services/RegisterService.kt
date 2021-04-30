package com.onlinestore.services

import com.onlinestore.db.dao.AuthInfoDao
import com.onlinestore.db.dao.UsersDao
import com.onlinestore.models.AuthInfo
import com.onlinestore.models.User
import org.springframework.beans.factory.annotation.Autowired
import com.onlinestore.service.models.LoginResult

class RegisterService(
    @Autowired val userDAO: UsersDao,
    @Autowired val authInfoDao: AuthInfoDao
) {
    fun register(phone: String, pwd: String, name: String, email: String): LoginResult {
        val existingUser = userDAO.findBy(username = phone)
        if (existingUser == null) {
            val user = User(email = email, phone = phone, pwd = pwd, name = name, balance = 15000)
            userDAO.save(user = user)
            val token = generateToken()
            authInfoDao.save(authInfo = AuthInfo(token = token, userId = user.id))
            return LoginResult(
                success = true,
                token = token,
                user = user
            )
        } else {
            throw ExistingEntityException()
        }
    }
}

class ExistingEntityException : Exception() {
}
package onlinestore.services

import onlinestore.db.dao.AuthInfoDao
import onlinestore.db.dao.UsersDao
import onlinestore.models.AuthInfo
import org.springframework.beans.factory.annotation.Autowired
import onlinestore.service.models.LoginResult

class LoginService(
    @Autowired val userDAO: UsersDao,
    @Autowired val authInfoDao: AuthInfoDao
) {
    fun login(username: String, pwd: String, ): LoginResult {
        val user = userDAO.findBy(username = username)
        val success = user?.pwd == pwd
        var token = ""
        if (success) {
            token = generateToken()
            authInfoDao.save(
                AuthInfo(token = token, userId = user!!.id)
            )
        }
        return LoginResult(
            success = success,
            token = token,
            user = user
        )
    }

    fun getAuthInfo(token: String): AuthInfo? =
        authInfoDao.findBy(token = token)

    fun updateAuthInfo(token: String, fcmToken: String, userId: Long) {
        authInfoDao.save(
            AuthInfo(
                token = token,
                fcmToken = fcmToken,
                userId = userId
            )
        )
    }
}
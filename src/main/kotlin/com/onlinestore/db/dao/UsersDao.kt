package com.onlinestore.db.dao

import com.j256.ormlite.dao.Dao
import com.onlinestore.models.User

class UsersDao(
    private val delegateDAO: Dao<User, Long>
) {
    fun findBy(username: String): User? =
        delegateDAO.queryBuilder().where()
            .eq("phone", username)
            .or()
            .eq("email", username)
            .query()
            .let{ result ->
                if (result.isNotEmpty()) {
                    result.first()
                } else {
                    null
                }
            }

    fun save(user: User) {
        delegateDAO.create(user)
    }
}
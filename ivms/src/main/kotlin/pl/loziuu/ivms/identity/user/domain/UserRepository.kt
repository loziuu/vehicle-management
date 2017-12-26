package pl.loziuu.ivms.identity.user.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findOneByLogin(login: String): User
}
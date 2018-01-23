package pl.loziuu.ivms.identity.user.application

import org.springframework.security.crypto.bcrypt.BCrypt.gensalt
import org.springframework.security.crypto.bcrypt.BCrypt.hashpw
import org.springframework.transaction.annotation.Transactional
import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.identity.user.domain.Role
import pl.loziuu.ivms.identity.user.domain.User
import pl.loziuu.ivms.identity.user.domain.UserRepository

@ApplicationService
@Transactional
class UserService(val repository: UserRepository) {

    fun addUser(login: String, password: String): Long {
        val hashedPassword = hashpw(password, gensalt())
        val user = User(login = login, password = hashedPassword)
        return repository.save(user).id
    }

    fun changePassword(id: Long, password: String) {
        val user = repository.findOne(id)
        user.changePassword(password)
    }

    fun changeRole(id: Long, role: Role) {
        val user = repository.findOne(id)
        user.changeRole(role)
    }

    fun activate(id: Long) {
        val user = repository.findOne(id)
        user.activate()
    }

    fun deactivate(id: Long) {
        val user = repository.findOne(id)
        user.deactivate()
    }
}
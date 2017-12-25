package pl.loziuu.ivms.identity.user.application

import org.springframework.transaction.annotation.Transactional
import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.identity.user.domain.User
import pl.loziuu.ivms.identity.user.domain.UserRepository

@ApplicationService
@Transactional(readOnly = true)
class UserQueryService(val repository: UserRepository) {

    fun getAll(): List<User> =
        repository.findAll()
}
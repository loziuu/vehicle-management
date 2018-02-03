package pl.loziuu.ivms.presentation.security

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import pl.loziuu.ivms.identity.user.domain.User
import pl.loziuu.ivms.identity.user.domain.UserRepository
import java.security.Principal

@RestController
@CrossOrigin
class AuthenticationController(val repository: UserRepository) {


    @GetMapping("/auth")
    fun getLogged(principal: Principal): User {
        return repository.findOneByLogin(principal.name)
    }
}
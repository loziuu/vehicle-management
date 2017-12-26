package pl.loziuu.ivms.presentation.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import pl.loziuu.ivms.identity.user.application.UserQueryService
import pl.loziuu.ivms.identity.user.application.UserService
import java.net.URI

@Service
class UserAdapter(@Autowired val queryService: UserQueryService,
                  @Autowired val commandService: UserService) {

    fun getAll() : ResponseEntity<Any> {
        return ResponseEntity.ok(queryService.getAll())
    }

    fun addUser(request: NewUserRequest): ResponseEntity<Any> {
        val createdUserId = commandService.addUser(request.login, request.password)
        return ResponseEntity.created(URI.create(createdUserId.toString())).build()
    }

    fun activate(id: Long): ResponseEntity<Any> {
        commandService.activate(id)
        return ResponseEntity.ok().build()
    }

    fun deactivate(id: Long): ResponseEntity<Any> {
        commandService.deactivate(id)
        return ResponseEntity.ok().build()
    }

    fun changePassword(id: Long, request: ChangePasswordRequest): ResponseEntity<Any> {
        commandService.changePassword(id, request.password)
        return ResponseEntity.ok().build()
    }

    fun changeRole(id: Long, request: ChangeRoleRequest): ResponseEntity<Any> {
        commandService.changeRole(id, request.role)
        return ResponseEntity.ok().build()
    }
}
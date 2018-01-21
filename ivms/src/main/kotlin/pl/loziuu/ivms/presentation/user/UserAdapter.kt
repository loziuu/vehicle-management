package pl.loziuu.ivms.presentation.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import pl.loziuu.ivms.identity.user.application.UserQueryService
import pl.loziuu.ivms.identity.user.application.UserService
import pl.loziuu.ivms.presentation.RestResponse
import pl.loziuu.ivms.presentation.user.requests.ChangePasswordRequest
import pl.loziuu.ivms.presentation.user.requests.ChangeRoleRequest
import pl.loziuu.ivms.presentation.user.requests.NewUserRequest

@Service
class UserAdapter(@Autowired val queryService: UserQueryService,
                  @Autowired val commandService: UserService) {

    fun getAll() : ResponseEntity<Any> = ResponseEntity.ok(queryService.getAll())

    fun addUser(request: NewUserRequest): ResponseEntity<Any> {
        commandService.addUser(request.login, request.password)
        return RestResponse("User created", 201).toResponseEntity()
    }

    fun activate(id: Long): ResponseEntity<Any> {
        commandService.activate(id)
        return RestResponse("User activated").toResponseEntity()
    }

    fun deactivate(id: Long): ResponseEntity<Any> {
        commandService.deactivate(id)
        return RestResponse("User deactivated").toResponseEntity()
    }

    fun changePassword(id: Long, request: ChangePasswordRequest): ResponseEntity<Any> {
        commandService.changePassword(id, request.password)
        return RestResponse("Password changed").toResponseEntity()
    }

    fun changeRole(id: Long, request: ChangeRoleRequest): ResponseEntity<Any> {
        commandService.changeRole(id, request.role)
        return RestResponse("Role changed").toResponseEntity()
    }
}
package pl.loziuu.ivms.presentation.user

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.presentation.user.requests.ChangePasswordRequest
import pl.loziuu.ivms.presentation.user.requests.ChangeRoleRequest
import pl.loziuu.ivms.presentation.user.requests.NewUserRequest

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasRole('ADMIN')")
class UserController(val adapter: UserAdapter) {

    @GetMapping
    fun getAll(): ResponseEntity<Any> = adapter.getAll()

    @PostMapping
    fun addNewUser(@RequestBody request: NewUserRequest): ResponseEntity<Any> = adapter.addUser(request)

    @PutMapping("{id}/activate")
    fun activateUser(@PathVariable id: Long): ResponseEntity<Any> = adapter.activate(id)

    @PutMapping("{id}/deactivate")
    fun deactivateUser(@PathVariable id: Long): ResponseEntity<Any> = adapter.deactivate(id)


    @PutMapping("{id}/change-password")
    fun changePassword(@PathVariable id: Long, @RequestBody request: ChangePasswordRequest): ResponseEntity<Any> =
            adapter.changePassword(id, request)

    @PutMapping("{id}/change-role")
    fun changeRole(@PathVariable id: Long, @RequestBody request: ChangeRoleRequest): ResponseEntity<Any> =
            adapter.changeRole(id, request)

}
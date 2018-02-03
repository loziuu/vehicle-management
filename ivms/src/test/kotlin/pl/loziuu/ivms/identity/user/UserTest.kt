package pl.loziuu.ivms.identity.user

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.security.crypto.bcrypt.BCrypt
import pl.loziuu.ivms.identity.user.domain.Role
import pl.loziuu.ivms.identity.user.domain.User
import javax.jws.soap.SOAPBinding

class UserTest {

    @Test
    fun shouldChangePassword() {
        val user = User()
        val newPassword = "test"

        user.changePassword(newPassword)

        assertThat(user.password).isEqualTo(newPassword)
    }

    @Test
    fun activateShouldEnableUser() {
        val user = User()

        user.activate()

        assertThat(user.enabled).isTrue()
    }

    @Test
    fun deactivateShouldDisableUser() {
        val user = User(enabled = true)

        user.deactivate()

        assertThat(user.enabled).isFalse()
    }

    @Test
    fun changeRoleShouldChangeUserRole() {
        val user = User()

        user.changeRole(Role.VISITOR)

        assertThat(user.role).isEqualTo(Role.VISITOR)
    }

    @Test
    fun hashedPassword() {
        print(BCrypt.hashpw("admin", BCrypt.gensalt()))
    }
}
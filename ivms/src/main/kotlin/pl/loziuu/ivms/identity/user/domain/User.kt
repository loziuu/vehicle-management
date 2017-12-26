package pl.loziuu.ivms.identity.user.domain

import javax.persistence.*

@Entity
@Table(name = "users")
class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
           @Enumerated(EnumType.STRING)
           var role: Role = Role.USER,
           @Column(unique = true)
           val login: String = "",
           var password: String = "",
           var enabled: Boolean = false) {

    fun activate() {
        enabled = true;
    }

    fun deactivate() {
        enabled = false
    }

    override fun toString(): String {
        return "User(id=$id, role=$role, login='$login', password='$password', enabled=$enabled)"
    }

    fun changePassword(password: String) {
        this.password = password;
    }

    fun changeRole(role: Role) {
        this.role = role
    }
}
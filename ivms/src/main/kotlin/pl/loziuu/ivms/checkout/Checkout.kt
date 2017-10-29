package pl.loziuu.ivms.checkout

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "checkout")
class Checkout(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val date: LocalDate = LocalDate.now(),
        val expirationDate: LocalDate = LocalDate.now(),
        @Enumerated(EnumType.STRING) val result: CheckoutResult = CheckoutResult.NEGATIVE,
        var vehicleId: Long) {

    fun isViable(): Boolean {
        return LocalDate.now().isBefore(expirationDate) && result.equals(CheckoutResult.POSITIVE);
    }
}

enum class CheckoutResult {
    POSITIVE, NEGATIVE
}

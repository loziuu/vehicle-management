package pl.loziuu.ivms.maintenance.checkout.domain

import pl.loziuu.ivms.maintenance.VehicleId
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "checkout")
class Checkout(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val date: LocalDate = LocalDate.now(),
        val expirationDate: LocalDate = LocalDate.now(),
        @Enumerated(EnumType.STRING) val result: CheckoutResult = CheckoutResult.NEGATIVE,
        var vehicleId: VehicleId = VehicleId()) {

    fun isViable(): Boolean {
        return LocalDate.now().isBefore(expirationDate) && result.equals(CheckoutResult.POSITIVE);
    }
}

enum class CheckoutResult {
    POSITIVE, NEGATIVE
}

package pl.loziuu.ivms.checkout

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "checkout")
class CheckoutQueryDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val date: LocalDate = LocalDate.now(),
        val expirationDate: LocalDate = LocalDate.now(),
        @Enumerated(EnumType.STRING) val result: CheckoutResult = CheckoutResult.NEGATIVE,
        val vehicleId: Long = 0) {

    @JsonIgnore
    fun isViable(): Boolean {
        return LocalDate.now().isBefore(expirationDate) && result.equals(CheckoutResult.POSITIVE);
    }
}

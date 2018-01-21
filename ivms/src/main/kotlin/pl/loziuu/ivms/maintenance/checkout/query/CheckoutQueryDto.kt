package pl.loziuu.ivms.maintenance.checkout.query

import com.fasterxml.jackson.annotation.JsonIgnore
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "checkout")
class CheckoutQueryDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val date: LocalDate = LocalDate.now(),
        val expirationDate: LocalDate = LocalDate.now(),
        @Enumerated(EnumType.STRING) val result: CheckoutResult = CheckoutResult.NEGATIVE,
        val journalId: Long = 0) {

    @JsonIgnore
    fun isViable(): Boolean = LocalDate.now().isBefore(expirationDate) && result.equals(CheckoutResult.POSITIVE)
}

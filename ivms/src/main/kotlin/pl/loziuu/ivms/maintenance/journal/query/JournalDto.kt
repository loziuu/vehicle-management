package pl.loziuu.ivms.maintenance.journal.query

import com.fasterxml.jackson.annotation.JsonIgnore
import pl.loziuu.ivms.maintenance.checkout.query.CheckoutQueryDto
import pl.loziuu.ivms.maintenance.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.maintenance.repair.query.RepairQueryDto
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "journal")
class JournalDto(
        @Id
        @JsonIgnore @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        @JsonIgnore val vehicleId: Long = 0,
        @OneToMany(mappedBy = "journalId", fetch = FetchType.EAGER) var repairs: MutableSet<RepairQueryDto> = HashSet(),
        @OneToMany(mappedBy = "journalId", fetch = FetchType.EAGER) var checkouts: MutableSet<CheckoutQueryDto> = HashSet(),
        @OneToMany(mappedBy = "journalId", fetch = FetchType.EAGER) var insurances: MutableSet<InsuranceQueryDto> = HashSet()) {

    fun hasActualInsurance(): Boolean {
        return insurances.firstOrNull { it -> it.endDate.isAfter(LocalDate.now()) } != null
    }

    fun hasValidCheckout(): Boolean {
        return checkouts.firstOrNull { it.isViable() } != null
    }

    fun willHaveActualInsuranceAt(date: LocalDate): Boolean {
        return insurances.firstOrNull { it -> it.endDate.isAfter(date) } != null
    }

    fun willHaveActualCheckoutAt(date: LocalDate): Boolean {
        return checkouts.firstOrNull { it -> it.isViable() && it.expirationDate.isAfter(date) } != null
    }

    fun sumRepairExpenses(): Double {
        return repairs.map { it -> it.cost }.sum()
    }
}

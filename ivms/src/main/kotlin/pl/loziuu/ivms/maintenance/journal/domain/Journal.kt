package pl.loziuu.ivms.maintenance.journal.domain

import pl.loziuu.ivms.ddd.Aggregate
import pl.loziuu.ivms.maintenance.checkout.domain.Checkout
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.insurance.domain.Company
import pl.loziuu.ivms.maintenance.insurance.domain.Insurance
import pl.loziuu.ivms.maintenance.insurance.domain.InsurancePeriod
import pl.loziuu.ivms.maintenance.repair.domain.Repair
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import java.time.LocalDate
import javax.persistence.*

@Entity
@Aggregate
@Table(name = "journal")
class Journal(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val vehicleId: Long = 0,
        @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, mappedBy = "journalId", fetch = FetchType.EAGER) var repairs: MutableSet<Repair> = HashSet(),
        @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, mappedBy = "journalId", fetch = FetchType.EAGER) var checkouts: MutableSet<Checkout> = HashSet(),
        @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, mappedBy = "journalId", fetch = FetchType.EAGER) var insurances: MutableSet<Insurance> = HashSet()) {

    fun registerRepair(repairDetails: RepairDetails) = repairs.add(Repair(0, repairDetails, id))

    fun registerInsurance(insurance: Insurance) {
        insurance.journalId = id
        insurances.add(insurance)
    }

    fun registerCheckout(checkout: Checkout) {
        checkout.journalId = id
        checkouts.add(checkout)
    }

    fun sumRepairExpenses(): Double = repairs.map { it -> it.getCost() }.sum()

    fun hasActualInsurance(): Boolean =
        insurances.firstOrNull { it -> it.getExpirationDate().isAfter(LocalDate.now()) } != null

    fun willHaveActualInsuranceAt(date: LocalDate): Boolean =
        insurances.firstOrNull { it -> it.getExpirationDate().isAfter(date) } != null

    fun willHaveActualCheckoutAt(date: LocalDate): Boolean =
        checkouts.firstOrNull { it -> it.isViable() && it.expirationDate.isAfter(date) } != null

    fun hasValidCheckout(): Boolean = checkouts.firstOrNull { it.isViable() } != null
}
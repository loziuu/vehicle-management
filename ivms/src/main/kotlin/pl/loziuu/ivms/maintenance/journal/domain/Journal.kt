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

    fun registerRepair(repairDetails: RepairDetails) {
        repairs.add(Repair(0, repairDetails, id))
    }

    fun registerInsurance(period: InsurancePeriod, company: Company) {
        insurances.add(Insurance(0, period, company, id))
    }

    fun registerCheckout(date: LocalDate, expDate: LocalDate, result: CheckoutResult) {
        checkouts.add(Checkout(0, date, expDate, result, id))
    }

    fun sumRepairExpenses(): Double {
        return repairs.map { it -> it.getCost() }.sum()
    }

    fun hasActualInsurance(): Boolean {
        return insurances.firstOrNull { it -> it.getExpirationDate().isAfter(LocalDate.now()) } != null
    }

    fun hasValidCheckout(): Boolean {
        return checkouts.firstOrNull { it.isViable() } != null
    }
}
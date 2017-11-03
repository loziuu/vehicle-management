package pl.loziuu.ivms.maintenance.journal.domain

import pl.loziuu.ivms.maintenance.checkout.domain.Checkout
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.insurance.domain.Company
import pl.loziuu.ivms.maintenance.insurance.domain.Insurance
import pl.loziuu.ivms.maintenance.insurance.domain.InsurancePeriod
import pl.loziuu.ivms.maintenance.repair.domain.Repair
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import java.time.LocalDate
import java.util.ArrayList
import javax.persistence.*

@Entity
@Table(name = "journal")
class Journal(
            @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
            val vehicleId: Long = 0,
            @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, mappedBy = "journalId") var repairs: MutableList<Repair> = ArrayList(),
            @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, mappedBy = "journalId") var checkouts: MutableList<Checkout> = ArrayList(),
            @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, mappedBy = "journalId") var insurances: MutableList<Insurance> = ArrayList()) {

    fun registerRepair(repairDetails: RepairDetails) {
        repairs.add(Repair(0, repairDetails, id))
    }

    fun registgerInsurance(period: InsurancePeriod, company: Company) {
        insurances.add(Insurance(0, period, company, id))
    }

    fun registerCheckout(date: LocalDate, expDate: LocalDate, result: CheckoutResult) {
        checkouts.add(Checkout(0, date, expDate, result, id))
    }

    fun sumRepairExpenses(): Double {
        return repairs.map { it -> it.getCost() }.sum()
    }
}
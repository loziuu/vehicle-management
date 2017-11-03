package pl.loziuu.ivms.management.vehicle.domain

import pl.loziuu.ivms.maintenance.checkout.domain.Checkout
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.insurance.domain.Insurance
import pl.loziuu.ivms.maintenance.VehicleId
import pl.loziuu.ivms.maintenance.repair.domain.Repair
import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "vehicle")
data class Vehicle(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) val id: Long = 0,
        val details: VehicleDetails = VehicleDetails(),
        @JoinColumn @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var repairs: MutableList<Repair> = ArrayList(),
        @JoinColumn @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var checkouts: MutableList<Checkout> = ArrayList(),
        @JoinColumn @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var insurances: MutableList<Insurance> = ArrayList()) {

    fun postRepair(repair: Repair) {
        repair.vehicleId = VehicleId(id)
        repairs.add(repair)
    }

    fun removeRepair(id: Long) {
        repairs.removeIf({ it.id == id })
    }

    fun postInsurance(insurance: Insurance) {
        insurance.vehicleId = VehicleId(id);
        insurances.add(insurance)
    }

    fun removeInsurance(id: Long) {
        insurances.removeIf({ it.id == id })
    }

    fun isInsured(): Boolean {
        val insurance = getCurrentInsurance()
        if (insurance.isPresent)
            return insurance.get().isActual()
        return false
    }

    fun sumRepairsCost(): Double = repairs.map { it.getCost() }.sum()

    private fun getCurrentInsurance(): Optional<Insurance> {
        return Optional.ofNullable(insurances.maxBy { insurance -> insurance.dateRange.endDate })
    }

    fun raportCheckout(date: LocalDate, expirationDate: LocalDate, result: CheckoutResult) {
        checkouts.add(Checkout(0, date, expirationDate, result, VehicleId(id)))
    }

    fun hasViableCheckout() : Boolean {
        val checkout = Optional.ofNullable(checkouts.maxBy { checkout -> checkout.expirationDate })
        if (checkout.isPresent)
            return checkout.get().isViable()
        return false
    }
}

package pl.loziuu.ivms.vehicle.domain

import pl.loziuu.ivms.insurance.domain.Insurance
import pl.loziuu.ivms.repair.domain.Repair
import java.util.*
import javax.persistence.*

@javax.persistence.Entity
@javax.persistence.Table(name = "vehicle")
data class Vehicle(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) val id: Long = 0,
        val details: VehicleDetails = VehicleDetails(),
        @JoinColumn @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var repairs: MutableList<Repair> = ArrayList(),
        @JoinColumn @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var insurances: MutableList<Insurance> = ArrayList()) {

    fun sumRepairsCost(): Double = repairs.map { it.getCost() }.sum()

    fun addRepair(repair: Repair) {
        repair.vehicleId = id
        repairs.add(repair)
    }

    fun deleteRepair(id: Long) {
        repairs.removeIf({ it.id == id })
    }

    fun addInsurance(insurance: Insurance) {
        insurance.vehicleId = this.id;
        insurances.add(insurance)
    }

    fun removeInsurance(id: Long) {
        insurances.removeIf({ it.id == id })
    }

    fun isInsuranced(): Boolean {
        val insurance = getLatestInsurance()
        if (insurance.isPresent)
            return insurance.get().isActual()
        return false
    }

    private fun getLatestInsurance(): Optional<Insurance> {
        return Optional.ofNullable(insurances.maxBy { insurance -> insurance.dateRange.endDate })
    }
}
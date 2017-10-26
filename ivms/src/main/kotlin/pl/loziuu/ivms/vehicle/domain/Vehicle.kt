package pl.loziuu.ivms.vehicle.domain

import pl.loziuu.ivms.insurance.domain.Insurance
import pl.loziuu.ivms.repair.domain.Repair
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "vehicle")
data class Vehicle(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) val id: Long = 0,
        val details: VehicleDetails = VehicleDetails(),
        @JoinColumn @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var repairs: MutableList<Repair> = ArrayList(),
        @JoinColumn @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true) var insurances: MutableList<Insurance> = ArrayList()) {

    fun postRepair(repair: Repair) {
        repair.vehicleId = id
        repairs.add(repair)
    }

    fun removeRepair(id: Long) {
        repairs.removeIf({ it.id == id })
    }

    fun postInsurance(insurance: Insurance) {
        insurance.vehicleId = this.id;
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
}

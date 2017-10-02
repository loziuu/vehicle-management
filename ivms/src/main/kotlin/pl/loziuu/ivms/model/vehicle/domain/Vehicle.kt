package pl.loziuu.ivms.model.vehicle.domain

import pl.loziuu.ivms.model.repair.domain.Repair
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@javax.persistence.Entity
@javax.persistence.Table(name = "vehicle")
data class Vehicle(
        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY) val id: Long = 0,
        val details: VehicleDetails = VehicleDetails(),
        @OneToMany var repairs: MutableSet<Repair> = HashSet()) {

    fun addRepair(repair: Repair) {
        repairs.add(repair)
    }
}
package pl.loziuu.ivms.maintenance.repair.domain

import pl.loziuu.ivms.maintenance.VehicleId
import javax.persistence.*

@Entity
@Table(name = "repair")
class Repair(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
             val id: Long = 0,
             val details: RepairDetails = RepairDetails(),
             var vehicleId: VehicleId = VehicleId()) {

    fun getCost(): Double = details.cost
}

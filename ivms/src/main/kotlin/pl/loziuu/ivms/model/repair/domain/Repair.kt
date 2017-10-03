package pl.loziuu.ivms.model.repair.domain

import javax.persistence.*

@Entity
@Table(name = "repair")
class Repair(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
             val id: Long = 0,
             val details: RepairDetails = RepairDetails(),
             var vehicleId: Long = 0) {

    fun getCost(): Double = details.cost
}

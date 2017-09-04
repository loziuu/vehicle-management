package pl.loziuu.ivms.model.repair.domain

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class RepairDto (@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                 val id: Long = 0,
                 val description: String = "",
                 val cost: Double = 0.0,
                 val vehicleId: Long = 0) {
    fun  toEntity(): Repair = Repair(this.id, this.description, this.cost, this.vehicleId)
}

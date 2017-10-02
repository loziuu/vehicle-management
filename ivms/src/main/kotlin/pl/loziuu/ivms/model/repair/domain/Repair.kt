package pl.loziuu.ivms.model.repair.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "repair")
class Repair (@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
              val id: Long = 0,
              val description: String = "",
              val cost: Double = 0.0,
              val date: LocalDate = LocalDate.now(),
              val vehicleId: Long = 0) {

    fun toDto(): RepairDto = RepairDto(this.id, this.description, this.cost, this.date, this.vehicleId)
}

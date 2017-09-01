package pl.loziuu.ivms.model.repair.query

import pl.loziuu.ivms.model.repair.domain.RepairDto
import javax.persistence.*

@Entity
@Table(name = "repair")
class RepairQueryDto (@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
              val id: Long = 0,
              val description: String = "",
              val cost: Double = 0.0,
              val vehicleId: Long = 0)

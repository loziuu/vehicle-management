package pl.loziuu.ivms.model.vehicle.query

import javax.persistence.*

@Entity
@Table(name = "vehicle")
class VehicleQueryDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0)

package pl.loziuu.ivms.model.vehicle.query

import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.model.repair.query.RepairQueryDto
import javax.persistence.*

@Entity
@Table(name = "vehicle")
class VehicleQueryDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0,
        @OneToMany(mappedBy = "vehicleId") val insurances: List<InsuranceQueryDto> = listOf(),
        @OneToMany(mappedBy = "vehicleId") val repairs: List<RepairQueryDto> = listOf())


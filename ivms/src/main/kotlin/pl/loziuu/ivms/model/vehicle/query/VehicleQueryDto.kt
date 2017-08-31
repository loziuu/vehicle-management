package pl.loziuu.ivms.model.vehicle.query

import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import javax.persistence.*

@Entity
@Table(name = "vehicle")
class VehicleQueryDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0,
        @OneToMany(mappedBy = "vehicleId") val insurances: List<InsuranceQueryDto> = listOf())


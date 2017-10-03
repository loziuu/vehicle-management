package pl.loziuu.ivms.model.vehicle.query

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pl.loziuu.ivms.model.insurance.exception.InsuranceNotFoundException
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
        @OneToMany(mappedBy = "vehicleId") val repairs: List<RepairQueryDto> = listOf()) {

    fun getInsurance(insuranceId: Long): InsuranceQueryDto {
        try {
            return insurances.filter { it.id == insuranceId }.first()
        } catch (e: NoSuchElementException) {
            throw InsuranceNotFoundException()
        }
    }

    fun getRepair(repairId: Long): RepairQueryDto {
        try {
            return repairs.filter { it.id == repairId }.first()
        } catch (e: NoSuchElementException) {
            throw RepairNotFoundException()
        }
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class RepairNotFoundException : RuntimeException()


package pl.loziuu.ivms.vehicle.query

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pl.loziuu.ivms.checkout.CheckoutQueryDto
import pl.loziuu.ivms.insurance.exception.InsuranceNotFoundException
import pl.loziuu.ivms.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.repair.query.RepairQueryDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "vehicle")
class VehicleQueryDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0,
        @OneToMany(mappedBy = "vehicleId") val insurances: Set<InsuranceQueryDto> = setOf(),
        @OneToMany(mappedBy = "vehicleId") val checkouts: Set<CheckoutQueryDto> = setOf(),
        @OneToMany(mappedBy = "vehicleId") val repairs: Set<RepairQueryDto> = setOf()) {

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

    fun getCheckout(checkoutId: Long): CheckoutQueryDto {
        try {
            return checkouts.filter { it.id == checkoutId }.first()
        } catch (e: NoSuchElementException) {
            throw CheckoutNotFoundException()
        }
    }

    @JsonIgnore
    fun isInsuranced(): Boolean {
        val insurance = getLatestInsurance()
        if (insurance.isPresent)
            return insurance.get().isActual()
        return false
    }

    private fun getLatestInsurance(): Optional<InsuranceQueryDto> {
        return Optional.ofNullable(insurances.maxBy { insurance -> insurance.endDate })
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class CheckoutNotFoundException : Throwable()

@ResponseStatus(HttpStatus.NOT_FOUND)
class RepairNotFoundException : RuntimeException()

    
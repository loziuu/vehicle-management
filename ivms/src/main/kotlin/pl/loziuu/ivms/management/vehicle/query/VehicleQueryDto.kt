package pl.loziuu.ivms.management.vehicle.query

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import pl.loziuu.ivms.maintenance.checkout.query.CheckoutQueryDto
import pl.loziuu.ivms.maintenance.insurance.exception.InsuranceNotFoundException
import pl.loziuu.ivms.maintenance.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.maintenance.repair.query.RepairQueryDto
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "vehicle")
class VehicleQueryDto(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
        val model: String = "",
        val manufacturer: String = "",
        val productionYear: Int = 0)

@ResponseStatus(HttpStatus.NOT_FOUND)
class CheckoutNotFoundException : Throwable()

@ResponseStatus(HttpStatus.NOT_FOUND)
class RepairNotFoundException : RuntimeException()

    
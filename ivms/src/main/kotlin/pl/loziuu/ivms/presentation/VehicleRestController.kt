package pl.loziuu.ivms.presentation

import org.springframework.hateoas.ResourceSupport
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceDto
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleRestPort

@RestController
@RequestMapping("v1/vehicles")
@CrossOrigin(origins = arrayOf("http://localhost:4200"))
class VehicleRestController(val restPort: VehicleRestPort) {

    @GetMapping
    fun getAllVehicles(): List<ResourceSupport> {
        return restPort.getAllVehicles()
    }

    @GetMapping("{id}")
    fun getSingleVehicle(@PathVariable id: Long): ResourceSupport {
        return restPort.getVehicle(id)
    }

    @GetMapping("{id}/repairs")
    fun getVehicleRepairs(@PathVariable id: Long): List<ResourceSupport> {
        return restPort.getVehicleRepairs(id)
    }

    @GetMapping("{vehicleId}/repairs/{repairId}")
    fun getVehicleRepair(@PathVariable vehicleId: Long, @PathVariable repairId: Long): ResourceSupport {
        return restPort.getVehicleRepair(vehicleId, repairId)
    }

    @GetMapping("{id}/insurances")
    fun getVehicleInsurances(@PathVariable id: Long): List<ResourceSupport> {
        return restPort.getVehicleInsurances(id)
    }

    @GetMapping("{vehicleId}/insurances/{insuranceId}")
    fun getVehicleInsurance(@PathVariable vehicleId: Long, @PathVariable insuranceId: Long): ResourceSupport {
        return restPort.getVehicleInsurance(vehicleId, insuranceId)
    }

    @GetMapping("{vehicleId}/checkouts")
    fun getVehicleCheckous(@PathVariable vehicleId: Long): List<ResourceSupport> {
        return restPort.getVehicleCheckouts(vehicleId)
    }

    @GetMapping("{vehicleId}/checkouts/{id}")
    fun getVehicleCheckout(@PathVariable vehicleId: Long, @PathVariable id: Long): ResourceSupport {
        return restPort.getVehicleCheckout(vehicleId, id)
    }

    @PostMapping
    fun addVehicle(@RequestBody details: VehicleDetails): ResponseEntity<Any> {
        return restPort.postVehicle(details)
    }

    @PostMapping("{id}/repairs")
    fun addRepair(@PathVariable id: Long, @RequestBody details: RepairDetails): ResponseEntity<Any> {
        return restPort.postRepair(id, details)
    }

    @PostMapping("{id}/insurances")
    fun addInsurance(@PathVariable id: Long, @RequestBody dto: InsuranceDto): ResponseEntity<Any> {
        return restPort.postInsurance(id, InsuranceDto(0, dto.startDate, dto.endDate, dto.company))
    }

    @DeleteMapping("{id}")
    fun deleteVehicle(@PathVariable id: Long): ResponseEntity<Any> {
        return restPort.deleteVehicle(id)
    }

    @DeleteMapping("{vehicleId}/repairs/{repairId}")
    fun deleteRepair(@PathVariable vehicleId: Long, @PathVariable repairId: Long): ResponseEntity<Any> {
        return restPort.deleteVehicleRepair(vehicleId, repairId)
    }

    @DeleteMapping("{vehicleId}/insurances/{insuranceId}")
    fun deleteInsurance(@PathVariable vehicleId: Long, @PathVariable insuranceId: Long): ResponseEntity<Any> {
        return restPort.deleteVehicleInsurance(vehicleId, insuranceId)
    }
}
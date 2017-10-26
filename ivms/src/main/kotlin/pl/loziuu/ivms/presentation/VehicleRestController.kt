package pl.loziuu.ivms.presentation

import org.springframework.hateoas.ResourceSupport
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.infrastructure.adapters.VehicleRestAdapter
import pl.loziuu.ivms.insurance.domain.InsuranceDto
import pl.loziuu.ivms.repair.domain.RepairDetails
import pl.loziuu.ivms.vehicle.domain.VehicleDetails

@RestController
@RequestMapping("v1/vehicles")
@CrossOrigin(origins = arrayOf("http://localhost:4200"))
class VehicleRestController(val restAdapter: VehicleRestAdapter) {

    @GetMapping
    fun getAllVehicles(): List<ResourceSupport> {
        return restAdapter.getAll();
    }

    @GetMapping("{id}")
    fun getSingleVehicle(@PathVariable id: Long): ResourceSupport {
        return restAdapter.get(id)
    }

    @GetMapping("{id}/repairs")
    fun getVehicleRepairs(@PathVariable id: Long): List<ResourceSupport> {
        return restAdapter.getVehicleRepairs(id)
    }

    @GetMapping("{vehicleId}/repairs/{repairId}")
    fun getVehicleRepair(@PathVariable vehicleId: Long, @PathVariable repairId: Long): ResourceSupport {
        return restAdapter.getVehicleRepair(vehicleId, repairId)
    }

    @GetMapping("{id}/insurances")
    fun getVehicleInsurances(@PathVariable id: Long): List<ResourceSupport> {
        return restAdapter.getVehicleInsurances(id)
    }

    @GetMapping("{vehicleId}/insurances/{insuranceId}")
    fun getVehicleInsurance(@PathVariable vehicleId: Long, @PathVariable insuranceId: Long): ResourceSupport {
        return restAdapter.getVehicleInsurance(vehicleId, insuranceId)
    }

    @PostMapping
    fun addVehicle(@RequestBody details: VehicleDetails): ResponseEntity<Any> {
        return restAdapter.postVehicle(details);
    }

    @PostMapping("{id}/repairs")
    fun addRepair(@PathVariable id: Long, @RequestBody details: RepairDetails): ResponseEntity<Any> {
        return restAdapter.postRepair(id, details);
    }

    @PostMapping("{id}/insurances")
    fun addInsurance(@PathVariable id: Long, @RequestBody dto: InsuranceDto): ResponseEntity<Any> {
        return restAdapter.postInsurance(id, InsuranceDto(0, dto.startDate, dto.endDate, dto.company))
    }

    @DeleteMapping("{id}")
    fun deleteVehicle(@PathVariable id: Long): ResponseEntity<Any> {
        return restAdapter.deleteVehicle(id)
    }

    @DeleteMapping("{vehicleId}/repairs/{repairId}")
    fun deleteRepair(@PathVariable vehicleId: Long, @PathVariable repairId: Long): ResponseEntity<Any> {
        return restAdapter.deleteVehicleRepair(vehicleId, repairId);
    }

    @DeleteMapping("{vehicleId}/insurances/{insuranceId}")
    fun deleteInsurance(@PathVariable vehicleId: Long, @PathVariable insuranceId: Long): ResponseEntity<Any> {
        return restAdapter.deleteVehicleInsurance(vehicleId, insuranceId)
    }
}
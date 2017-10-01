package pl.loziuu.ivms.model.endpoints

import org.springframework.hateoas.ResourceSupport
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.repair.domain.RepairDto
import pl.loziuu.ivms.model.vehicle.adapters.VehicleRestAdapter
import pl.loziuu.ivms.model.vehicle.domain.VehicleDetails
import java.time.LocalDate

@RestController
@RequestMapping("v1/vehicles")
@CrossOrigin(origins = arrayOf("http://localhost:4200"))
class VehicleRestController(val restAdapter: VehicleRestAdapter){

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
    fun addRepair(@PathVariable id: Long, @RequestBody dto: RepairDto): ResponseEntity<Any> {
        val vdto = RepairDto(0, dto.description, dto.cost, LocalDate.now(), id)
        return restAdapter.postRepair(vdto);
    }

    @PostMapping("{id}/insurances")
    fun addInsurance(@PathVariable id: Long, @RequestBody dto: InsuranceDto): ResponseEntity<Any> {
        return restAdapter.postInsurance(InsuranceDto(0, dto.startDate, dto.endDate, dto.company, id))
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
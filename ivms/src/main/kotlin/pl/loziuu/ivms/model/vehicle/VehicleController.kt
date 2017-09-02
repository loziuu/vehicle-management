package pl.loziuu.ivms.model.vehicle

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.model.repair.query.RepairQueryDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleFacade
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryDto

@RestController
@RequestMapping("/vehicles")
class VehicleController(val facade: VehicleFacade) {

    @GetMapping
    fun getAll() = facade.getAll()

    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long): ResponseEntity<VehicleQueryDto> {
        val vehicle = facade.get(id)
        return ResponseEntity(vehicle, HttpStatus.OK)
    }

    @GetMapping("{id}/insurances")
    fun getVehicleInsurances(@PathVariable id: Long): ResponseEntity<List<InsuranceQueryDto>> {
        val entities = facade.get(id).insurances
        return ResponseEntity(entities, HttpStatus.OK)
    }

    @GetMapping("{id}/repairs")
    fun getVehicleRepairs(@PathVariable id:Long): ResponseEntity<List<RepairQueryDto>> {
        val entities = facade.get(id).repairs
        return ResponseEntity(entities, HttpStatus.OK)
    }

    @PostMapping
    fun add(@RequestBody dto: VehicleDto): ResponseEntity<VehicleDto> {
        val vehicle = facade.add(dto)
        return ResponseEntity(vehicle, HttpStatus.CREATED)
    }

    @PostMapping("{id}/insurances")
    fun addInsurance(@PathVariable id: Long, @RequestBody dto: InsuranceDto): ResponseEntity<InsuranceDto> {
        val entity = facade.addInsurance(InsuranceDto(dto.id, dto.startDate, dto.endDate, id))
        return ResponseEntity(entity, HttpStatus.CREATED)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        facade.delete(id)
        return ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{vehicleId}/insurances/{insuranceId}")
    fun deleteInsurance(@PathVariable vehicleId: Long, @PathVariable insuranceId: Long): ResponseEntity<Any> {
        facade.deleteInsurance(vehicleId, insuranceId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}


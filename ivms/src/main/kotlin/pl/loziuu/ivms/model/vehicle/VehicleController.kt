package pl.loziuu.ivms.model.vehicle

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.model.repair.domain.RepairDto
import pl.loziuu.ivms.model.repair.query.RepairQueryDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleFacade
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryDto

@RestController
@RequestMapping("/vehicles")
@CrossOrigin(origins = arrayOf("http://localhost:4200"))
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

    @GetMapping("{id}/insurances/{insuranceId}")
    fun getVehicleInsurance(@PathVariable id: Long, @PathVariable insuranceId: Long): ResponseEntity<InsuranceQueryDto> {
        val entities = facade.get(id).getInsurance(insuranceId)
        return ResponseEntity(entities, HttpStatus.OK)
    }

    @GetMapping("{id}/repairs")
    fun getVehicleRepairs(@PathVariable id: Long): ResponseEntity<List<RepairQueryDto>> {
        val entities = facade.get(id).repairs
        return ResponseEntity(entities, HttpStatus.OK)
    }

    @GetMapping("{id}/repairs/{repairId}")
    fun getVehicleRepair(@PathVariable id: Long, @PathVariable repairId: Long): ResponseEntity<RepairQueryDto> {
        val entities = facade.get(id).getRepair(repairId)
        return ResponseEntity(entities, HttpStatus.OK)
    }

    @PostMapping
    fun add(@RequestBody dto: VehicleDto): ResponseEntity<VehicleDto> {
        val vehicle = facade.add(dto)
        return ResponseEntity(vehicle, HttpStatus.CREATED)
    }

    @PostMapping("{id}/insurances")
    fun addInsurance(@PathVariable id: Long, @RequestBody dto: InsuranceDto): ResponseEntity<InsuranceDto> {
        val entity = facade.addInsurance(InsuranceDto(0, dto.startDate, dto.endDate, dto.company, id))
        return ResponseEntity(entity, HttpStatus.CREATED)
    }

    @PostMapping("{id}/repairs")
    fun addRepair(@PathVariable id: Long, @RequestBody dto: RepairDto): ResponseEntity<RepairDto> {
        val repair = facade.addRepair(RepairDto(0, dto.description, dto.cost, dto.date, id))
        return ResponseEntity(repair, HttpStatus.CREATED)
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

    @DeleteMapping("{vehicleId}/repairs/{repairId}")
    fun deleteRepair(@PathVariable vehicleId: Long, @PathVariable repairId: Long): ResponseEntity<Any> {
        facade.deleteRepair(vehicleId, repairId)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}


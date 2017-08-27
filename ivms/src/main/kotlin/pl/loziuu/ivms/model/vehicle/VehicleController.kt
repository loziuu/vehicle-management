package pl.loziuu.ivms.model.vehicle

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleFacade

@RestController
@RequestMapping("/vehicles")
class VehicleController(val facade: VehicleFacade) {

    @GetMapping
    fun getAll() = facade.getAll()

    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long) = facade.get(id)

    @PostMapping
    fun add(@RequestBody dto: VehicleDto): ResponseEntity<VehicleDto> {
        val vehicle = facade.add(dto)
        return ResponseEntity(vehicle, HttpStatus.CREATED)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        facade.delete(id)
        return ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("{id}/insurances")
    fun addInsurance(@PathVariable id: Long, @RequestBody dto: InsuranceDto): ResponseEntity<InsuranceDto> {
        val entity = facade.addInsurance(InsuranceDto(dto.id, dto.startDate, dto.endDate, id))
        return ResponseEntity(entity, HttpStatus.CREATED)
    }
}


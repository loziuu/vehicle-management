package pl.loziuu.ivms.model.vehicle

import com.sun.xml.internal.ws.client.sei.ResponseBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.loziuu.ivms.model.vehicle.domain.VehicleDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleService
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryService

@RestController
@RequestMapping("/vehicles")
class VehicleController(val command: VehicleService, val query: VehicleQueryService) {

    @GetMapping
    fun getAll() = query.getAll()

    @GetMapping("{id}")
    fun getOne(@PathVariable id: Long) = query.get(id)

    @PostMapping
    fun add(@RequestBody dto: VehicleDto): ResponseEntity<VehicleDto> {
        val vehicle = command.add(dto)
        return ResponseEntity(vehicle, HttpStatus.CREATED)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        command.delete(id)
        return ResponseEntity(null, HttpStatus.NO_CONTENT);
    }

}
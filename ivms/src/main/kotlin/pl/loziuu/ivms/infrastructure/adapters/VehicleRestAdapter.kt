package pl.loziuu.ivms.infrastructure.adapters

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import pl.loziuu.ivms.model.endpoints.VehicleRestController
import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.model.repair.domain.RepairDetails
import pl.loziuu.ivms.model.repair.query.RepairQueryDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.model.vehicle.domain.VehicleFacade
import pl.loziuu.ivms.model.vehicle.ports.VehicleRestPort
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryDto
import java.net.URI

class VehicleRestAdapter(val facade: VehicleFacade) : VehicleRestPort {

    override fun get(id: Long): ResourceSupport {
        val vehicle = facade.get(id)
        return VehicleResource(vehicle)
    }

    override fun getVehicleRepair(vehicleId: Long, repairId: Long): ResourceSupport {
        val repair = facade.get(vehicleId).getRepair(repairId);
        return RepairResource(repair)
    }

    override fun getVehicleInsurance(vehicleId: Long, insuranceId: Long): ResourceSupport {
        val insurance = facade.get(vehicleId).getInsurance(insuranceId)
        return InsuranceResource(insurance)
    }

    override fun getAll(): List<ResourceSupport> {
        val vehicles = facade.getAll();
        return vehicles.map { v -> VehicleResource(v) }
    }

    override fun getVehicleRepairs(id: Long): List<ResourceSupport> {
        val repairs = facade.get(id).repairs
        return repairs.map { r -> RepairResource(r) }
    }

    override fun getVehicleInsurances(id: Long): List<ResourceSupport> {
        val insurances = facade.get(id).insurances
        return insurances.map { i -> InsuranceResource(i) }
    }

    override fun postVehicle(details: VehicleDetails): ResponseEntity<Any> {
        val id = facade.add(details)
        val vehicle = facade.get(id)
        val link = VehicleResource(vehicle).getLink("self")
        return ResponseEntity.created(URI.create(link.href)).build()
    }

    override fun postRepair(vehicleId: Long, details: RepairDetails): ResponseEntity<Any> {
        facade.addRepair(vehicleId, details)
        return ResponseEntity.status(201).build()
    }

    override fun postInsurance(vehicleId: Long, dto: InsuranceDto): ResponseEntity<Any> {
        facade.addInsurance(vehicleId, dto)
        return ResponseEntity.status(201).build()
    }

    override fun deleteVehicle(id: Long): ResponseEntity<Any> {
        facade.delete(id)
        return ResponseEntity.noContent().build()
    }

    override fun deleteVehicleRepair(vehicleId: Long, repairId: Long): ResponseEntity<Any> {
        facade.deleteRepair(vehicleId, repairId)
        return ResponseEntity.noContent().build()
    }

    override fun deleteVehicleInsurance(vehicleId: Long, insuranceId: Long): ResponseEntity<Any> {
        facade.deleteInsurance(vehicleId, insuranceId)
        return ResponseEntity.noContent().build()
    }
}

class VehicleResource(val content: VehicleQueryDto) : ResourceSupport() {

    init {
        add(linkTo(methodOn(VehicleRestController::class.java).getSingleVehicle(content.id)).withRel("self"))
        add(linkTo(methodOn(VehicleRestController::class.java).getVehicleRepairs(content.id)).withRel("repairs"))
        add(linkTo(methodOn(VehicleRestController::class.java).getVehicleInsurances(content.id)).withRel("insurances"))
    }
}

class RepairResource(val content: RepairQueryDto) : ResourceSupport() {

    init {
        add(linkTo(methodOn(VehicleRestController::class.java).getVehicleRepair(content.vehicleId, content.id)).withRel("self"))
    }
}

class InsuranceResource(val content: InsuranceQueryDto) : ResourceSupport() {

    init {
        add(linkTo(methodOn(VehicleRestController::class.java).getVehicleInsurance(content.vehicleId, content.id)).withRel("self"))
    }
}

package pl.loziuu.ivms.infrastructure.adapters

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import pl.loziuu.ivms.endpoints.VehicleRestController
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.model.repair.query.RepairQueryDto
import pl.loziuu.ivms.model.vehicle.query.VehicleQueryDto

class Resources {
}

class VehicleResource(val content: VehicleQueryDto) : ResourceSupport() {

    init {
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getSingleVehicle(content.id)).withRel("self"))
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleRepairs(content.id)).withRel("repairs"))
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleInsurances(content.id)).withRel("insurances"))
    }
}

class RepairResource(val content: RepairQueryDto) : ResourceSupport() {

    init {
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleRepair(content.vehicleId, content.id)).withRel("self"))
    }
}

class InsuranceResource(val content: InsuranceQueryDto) : ResourceSupport() {

    init {
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleInsurance(content.vehicleId, content.id)).withRel("self"))
    }
}

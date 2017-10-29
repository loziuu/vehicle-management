package pl.loziuu.ivms.infrastructure.adapters

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import pl.loziuu.ivms.checkout.CheckoutQueryDto
import pl.loziuu.ivms.presentation.VehicleRestController
import pl.loziuu.ivms.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.repair.query.RepairQueryDto
import pl.loziuu.ivms.vehicle.query.VehicleQueryDto

class Resources {
}

class VehicleResource(val content: VehicleQueryDto) : ResourceSupport() {

    init {
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getSingleVehicle(content.id)).withRel("self"))
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleRepairs(content.id)).withRel("repairs"))
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleInsurances(content.id)).withRel("insurances"))
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleCheckous(content.id)).withRel("checkouts"))
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

class CheckoutResoruce(val content: CheckoutQueryDto) : ResourceSupport() {

    init {
        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleCheckout(content.vehicleId, content.id)).withRel("self"))
    }
}

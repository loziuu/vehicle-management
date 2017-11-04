package pl.loziuu.ivms.management.infrastructure.adapters

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import pl.loziuu.ivms.maintenance.checkout.query.CheckoutQueryDto
import pl.loziuu.ivms.maintenance.insurance.query.InsuranceQueryDto
import pl.loziuu.ivms.maintenance.repair.query.RepairQueryDto
import pl.loziuu.ivms.management.vehicle.query.VehicleQueryDto

class Resources {
}

class VehicleResource(val content: VehicleQueryDto) : ResourceSupport() {

    init {
//        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getSingleVehicle(content.id)).withRel("self"))
//        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleRepairs(content.id)).withRel("repairs"))
//        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleInsurances(content.id)).withRel("insurances"))
//        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleCheckous(content.id)).withRel("checkouts"))
    }
}

class RepairResource(val content: RepairQueryDto) : ResourceSupport() {

    init {
//        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleRepair(content.journalId, content.id)).withRel("self"))
    }
}

class InsuranceResource(val content: InsuranceQueryDto) : ResourceSupport() {

    init {
//        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleInsurance(content.journalId, content.id)).withRel("self"))
    }
}

class CheckoutResoruce(val content: CheckoutQueryDto) : ResourceSupport() {

    init {
//        add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(VehicleRestController::class.java).getVehicleCheckout(content.journalId, content.id)).withRel("self"))
    }
}

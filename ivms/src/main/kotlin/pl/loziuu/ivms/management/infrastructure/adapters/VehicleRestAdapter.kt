package pl.loziuu.ivms.management.infrastructure.adapters

import org.springframework.hateoas.ResourceSupport
import org.springframework.http.ResponseEntity
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleRestPort

import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceDto
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleQueryService
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleService
import java.net.URI

class VehicleRestAdapter(val command: VehicleService,
                         val query: VehicleQueryService) : VehicleRestPort {

    override fun getVehicleCheckouts(vehicleId: Long): List<ResourceSupport> {
        val vehicle = query.get(vehicleId)
        return vehicle.checkouts.map { it -> CheckoutResoruce(it) }
    }

    override fun getVehicleCheckout(vehicleId: Long, id: Long): ResourceSupport {
        val vehicle = query.get(vehicleId)
        return CheckoutResoruce(vehicle.getCheckout(id))
    }

    override fun getVehicle(id: Long): ResourceSupport {
        val vehicle = query.get(id)
        return VehicleResource(vehicle)
    }

    override fun getVehicleRepair(vehicleId: Long, repairId: Long): ResourceSupport {
        val repair = query.get(vehicleId).getRepair(repairId);
        return RepairResource(repair)
    }

    override fun getVehicleInsurance(vehicleId: Long, insuranceId: Long): ResourceSupport {
        val insurance = query.get(vehicleId).getInsurance(insuranceId)
        return InsuranceResource(insurance)
    }

    override fun getAllVehicles(): List<ResourceSupport> {
        val vehicles = query.getAll();
        return vehicles.map { v -> VehicleResource(v) }
    }

    override fun getVehicleRepairs(id: Long): List<ResourceSupport> {
        val repairs = query.get(id).repairs
        return repairs.map { r -> RepairResource(r) }
    }

    override fun getVehicleInsurances(id: Long): List<ResourceSupport> {
        val insurances = query.get(id).insurances
        return insurances.map { i -> InsuranceResource(i) }
    }

    override fun postVehicle(details: VehicleDetails): ResponseEntity<Any> {
        val id = command.add(details)
        val vehicle = query.get(id)
        val link = VehicleResource(vehicle).getLink("self")
        return ResponseEntity.created(URI.create(link.href)).build()
    }

    override fun postRepair(vehicleId: Long, details: RepairDetails): ResponseEntity<Any> {
        command.addRepair(vehicleId, details)
        return ResponseEntity.status(201).build()
    }

    override fun postInsurance(vehicleId: Long, dto: InsuranceDto): ResponseEntity<Any> {
        command.addInsurance(vehicleId, dto)
        return ResponseEntity.status(201).build()
    }

    override fun deleteVehicle(id: Long): ResponseEntity<Any> {
        command.delete(id)
        return ResponseEntity.noContent().build()
    }

    override fun deleteVehicleRepair(vehicleId: Long, repairId: Long): ResponseEntity<Any> {
        command.deleteRepair(vehicleId, repairId)
        return ResponseEntity.noContent().build()
    }

    override fun deleteVehicleInsurance(vehicleId: Long, insuranceId: Long): ResponseEntity<Any> {
        command.removeInsurance(vehicleId, insuranceId)
        return ResponseEntity.noContent().build()
    }
}

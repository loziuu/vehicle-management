package pl.loziuu.ivms.infrastructure.adapters

import org.hibernate.annotations.Check
import org.springframework.hateoas.ResourceSupport
import org.springframework.http.ResponseEntity
import pl.loziuu.ivms.insurance.domain.InsuranceDto
import pl.loziuu.ivms.repair.domain.RepairDetails
import pl.loziuu.ivms.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.vehicle.domain.VehicleFacade
import pl.loziuu.ivms.vehicle.ports.primary.VehicleRestPort
import java.net.URI

class VehicleRestAdapter(val facade: VehicleFacade) : VehicleRestPort {

    override fun getVehicleCheckouts(vehicleId: Long): List<ResourceSupport> {
        val vehicle = facade.get(vehicleId)
        return vehicle.checkouts.map { it -> CheckoutResoruce(it) }
    }

    override fun getVehicleCheckout(vehicleId: Long, id: Long): ResourceSupport {
        val vehicle = facade.get(vehicleId)
        return CheckoutResoruce(vehicle.getCheckout(id))
    }

    override fun getVehicle(id: Long): ResourceSupport {
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

    override fun getAllVehicles(): List<ResourceSupport> {
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

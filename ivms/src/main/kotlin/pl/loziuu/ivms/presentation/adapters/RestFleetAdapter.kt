package pl.loziuu.ivms.presentation.adapters

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import fuzzy4j.flc.Variable
import org.aspectj.weaver.ast.Var
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import pl.loziuu.ivms.inference.application.InferenceService
import pl.loziuu.ivms.maintenance.application.MaintenanceQueryService
import pl.loziuu.ivms.maintenance.journal.domain.Journal
import pl.loziuu.ivms.maintenance.journal.query.JournalDto
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.vehicle.query.VehicleDto

@Service
class RestFleetAdapter(val managementQueryService: ManagementQueryService,
                       val maintenanceQueryService: MaintenanceQueryService,
                       val inferenceService: InferenceService) {

    fun getFleets(): ResponseEntity<Any> {
        return ResponseEntity.ok(managementQueryService.getAllFleets().map { it -> FleetResource(it.id, it.name) })
    }

    fun getFleetVehicles(id: Long): List<VehicleResource> {
        return managementQueryService.getFleet(id).vehicles.map { VehicleResource(it.local, it.model, it.manufacturer, it.productionYear, it.journal)  }
    }

    fun getFleetVehicle(id: Long, vehicleId: Long): VehicleDto {
        val vehicle = managementQueryService.getFleet(id).getVehicle(vehicleId)
        return vehicle
    }

    fun getFleet(id: Long) : ResponseEntity<Any> {
        val fleet = managementQueryService.getFleet(id)
        val status = inferenceService.getFleetStatus(id)
        val journals = maintenanceQueryService.getJournalsForFleet(id)
        return ResponseEntity.ok(FleetResource(fleet.id, fleet.name, status, journals))
    }
}

class FleetResource(val id: Long = 0,
                    val name: String = "",
                    val status : MutableMap<Variable, Double>? = hashMapOf(),
                    @JsonIgnore val journals : List<JournalDto> = emptyList()) {

    fun getVehiclesWithoutInsurance() : Int {
        return journals.filter { !it.hasActualInsurance() }.count()
    }

    fun getVehiclesWithoutCheckout() : Int {
        return journals.filter { !it.hasValidCheckout() }.count()
    }
}

class VehicleResource(val id: Long = 0,
                      val model: String = "",
                      val manufacturer: String = "",
                      val productionYear: Int = 0,
                      @JsonIgnore val journal: MutableSet<JournalDto> = HashSet()) {

    fun getHasActualInsurance() : Boolean {
        return journal.first().hasActualInsurance()
    }

    fun getHasValidCheckout() : Boolean {
        return journal.first().hasValidCheckout()
    }

    fun getTotalRepairsCost() : Double {
        return journal.first().sumRepairExpenses()
    }
}

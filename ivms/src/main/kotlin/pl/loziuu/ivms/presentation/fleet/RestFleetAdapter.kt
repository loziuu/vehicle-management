package pl.loziuu.ivms.presentation.fleet

import com.fasterxml.jackson.annotation.JsonIgnore
import fuzzy4j.flc.Variable
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import pl.loziuu.ivms.inference.application.InferenceService
import pl.loziuu.ivms.inference.fuzzy.FuzzyRaport
import pl.loziuu.ivms.maintenance.application.MaintenanceQueryService
import pl.loziuu.ivms.maintenance.journal.query.JournalDto
import pl.loziuu.ivms.management.application.ManagementQueryService
import pl.loziuu.ivms.management.fleet.query.FleetDto
import pl.loziuu.ivms.management.vehicle.query.VehicleDto
import java.time.LocalDate

@Service
class RestFleetAdapter(val managementQueryService: ManagementQueryService,
                       val maintenanceQueryService: MaintenanceQueryService,
                       val inferenceService: InferenceService) {

    fun getFleets(): ResponseEntity<Any> =
            ResponseEntity.ok(managementQueryService.getAllFleets()
                    .map { it -> FleetResource(it, status = inferenceService.getFutureFleetStatusForDate(it.id, LocalDate.now())) })


    fun getFleetVehicles(id: Long): Set<VehicleDto> =
            managementQueryService.getFleet(id).vehicles

    fun getFleetVehicle(id: Long, vehicleId: Long): VehicleDto =
            managementQueryService.getFleet(id).getVehicle(vehicleId)


    fun getFleet(id: Long): ResponseEntity<Any> =
            getFutureFleet(id, LocalDate.now());

    fun getFutureFleet(id: Long, date: LocalDate): ResponseEntity<Any> {
        val fleet = managementQueryService.getFleet(id)
        val status = inferenceService.getFutureFleetStatusForDate(id, date)
        val journals = maintenanceQueryService.getJournalsForFleet(id)
        val vehicles = managementQueryService.getFleet(id).vehicles
        return ResponseEntity.ok(FutureFleetResource(fleet, journals, vehicles, date, status))
    }
}

class FleetResource(val id: Long = 0,
                    val name: String = "",
                    @JsonIgnore val journals: List<JournalDto> = emptyList(),
                    @JsonIgnore val vehicles: MutableSet<VehicleDto>,
                    var status: FuzzyRaport) {

    constructor(dto: FleetDto, status: FuzzyRaport) : this(dto.id, dto.name, vehicles = dto.vehicles, status = status)

    fun getVehiclesWithoutInsurance(): Int =
            journals.filter { !it.hasActualInsurance() }.count()


    fun getVehiclesWithoutCheckout(): Int =
            journals.filter { !it.hasValidCheckout() }.count()

    fun getVehiclesCount(): Int =
            vehicles.size
}

class VehicleResource(val id: Long = 0,
                      val model: String = "",
                      val manufacturer: String = "",
                      val productionYear: Int = 0,
                      @JsonIgnore val journal: MutableSet<JournalDto> = HashSet()) {

    fun getHasActualInsurance(): Boolean =
            journal.first().hasActualInsurance()


    fun getHasValidCheckout(): Boolean =
            journal.first().hasValidCheckout()


    fun getTotalRepairsCost(): Double =
            journal.first().sumRepairExpenses()

}

class FutureFleetResource(@JsonIgnore val fleet: FleetDto = FleetDto(),
                          @JsonIgnore val journals: List<JournalDto> = emptyList(),
                          @JsonIgnore val vehiclesDto: MutableSet<VehicleDto> = mutableSetOf(),
                          val date: LocalDate,
                          val status: FuzzyRaport) {

    fun getId(): Long = fleet.id
    fun getName(): String = fleet.name
    fun getVehicles(): List<FutureVehicleResource> {
        return vehiclesDto.map { it ->
            FutureVehicleResource(
                    it.local, it.model, it.manufacturer, it.productionYear,
                    it.journal.first().willHaveActualInsuranceAt(date),
                    it.journal.first().willHaveActualCheckoutAt(date))
        }.toList()
    }

    fun getVehiclesWithoutInsurance(): Int =
            journals.filter { !it.willHaveActualInsuranceAt(date) }.count()


    fun getVehiclesWithoutCheckout(): Int =
            journals.filter { !it.willHaveActualCheckoutAt(date) }.count()

}

class FutureVehicleResource(val id: Long = 0,
                            val model: String = "",
                            val manufacturer: String = "",
                            val productionYear: Int = 0,
                            val hasActualInsurance: Boolean = false,
                            val hasValidCheckout: Boolean = false)


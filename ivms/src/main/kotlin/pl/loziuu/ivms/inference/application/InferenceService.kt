package pl.loziuu.ivms.inference.application

import fuzzy4j.flc.Variable
import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.inference.fuzzy.FleetLogic
import pl.loziuu.ivms.maintenance.application.MaintenanceQueryService
import pl.loziuu.ivms.maintenance.journal.domain.Journal

@ApplicationService
class InferenceService(val maintenanceService: MaintenanceQueryService) {

    fun getFleetStatus(fleetId: Long): MutableMap<Variable, Double>? {
        val journals = maintenanceService.getJournalsForFleet(fleetId)
        val withoutInsurance = getPercentageForNotInsured(journals)
        val withoutCheckout = getPercentageForNotCheckouted(journals)
        return FleetLogic.getStatus(withoutInsurance, withoutCheckout)
    }

    private fun getPercentageForNotCheckouted(journals: List<Journal>) =
            (journals.count { !it.hasValidCheckout() }.toDouble() / journals.size.toDouble()) * 100.0

    private fun getPercentageForNotInsured(journals: List<Journal>) =
            (journals.count { !it.hasActualInsurance() }.toDouble() / journals.size.toDouble()) * 100.0
}
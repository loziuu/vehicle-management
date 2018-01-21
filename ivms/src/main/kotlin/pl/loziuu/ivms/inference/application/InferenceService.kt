package pl.loziuu.ivms.inference.application

import fuzzy4j.flc.Variable
import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.inference.fuzzy.FleetLogic
import pl.loziuu.ivms.maintenance.application.MaintenanceQueryService
import pl.loziuu.ivms.maintenance.journal.query.JournalDto
import java.time.LocalDate

@ApplicationService
class InferenceService(val maintenanceService: MaintenanceQueryService) {

    fun getFleetStatus(fleetId: Long): MutableMap<Variable, Double>? {
        val journals = maintenanceService.getJournalsForFleet(fleetId)
        val withoutInsurance = getPercentageForNotInsured(journals)
        val withoutCheckout = getPercentageForNotCheckouted(journals)
        return FleetLogic.getStatus(withoutInsurance, withoutCheckout)
    }

    fun getFutureFleetStatusForDate(fleetId: Long, date: LocalDate): MutableMap<Variable, Double>? {
        val journals = maintenanceService.getJournalsForFleet(fleetId)
        val withoutInsurance = getPercentageForNotInsured(journals, date)
        val withoutCheckout = getPercentageForNotCheckouted(journals, date)
        return FleetLogic.getStatus(withoutInsurance, withoutCheckout)
    }

    private fun getPercentageForNotCheckouted(journals: List<JournalDto>, date: LocalDate = LocalDate.now()) =
            (journals.count { !it.willHaveActualCheckoutAt(date) }.toDouble() / journals.size.toDouble()) * 100.0

    private fun getPercentageForNotInsured(journals: List<JournalDto>, date: LocalDate = LocalDate.now()) =
            (journals.count { !it.willHaveActualInsuranceAt(date) }.toDouble() / journals.size.toDouble()) * 100.0
}
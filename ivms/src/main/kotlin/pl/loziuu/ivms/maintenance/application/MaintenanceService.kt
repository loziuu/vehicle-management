package pl.loziuu.ivms.maintenance.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.checkout.ports.CheckoutService
import pl.loziuu.ivms.maintenance.insurance.domain.Company
import pl.loziuu.ivms.maintenance.insurance.domain.InsurancePeriod
import pl.loziuu.ivms.maintenance.insurance.ports.InsuranceService
import pl.loziuu.ivms.maintenance.journal.domain.Journal
import pl.loziuu.ivms.maintenance.journal.domain.JournalRepository
import pl.loziuu.ivms.maintenance.journal.port.JournalSetupCommand
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.maintenance.repair.ports.primary.RepairService
import java.time.LocalDate
import javax.transaction.Transactional

@Transactional
@ApplicationService
class MaintenanceService(val repository: JournalRepository) : JournalSetupCommand, InsuranceService, RepairService, CheckoutService {

    override fun setupJournal(vehicleId: Long): Long {
        return repository.save(Journal(vehicleId = vehicleId)).id
    }

    override fun removeJournal(vehicleId: Long) {
        repository.deleteByVehicleId(vehicleId)
    }

    override fun registerInsurance(vehicleId: Long, startDate: LocalDate, endDate: LocalDate, companyName: String) {
        val journal = repository.findOneByVehicleId(vehicleId)
        journal.registerInsurance(InsurancePeriod(startDate, endDate), Company(companyName))
        repository.save(journal)
    }

    override fun registerRepair(vehicleId: Long, description: String, date: LocalDate, cost: Double) {
        val journal = repository.findOneByVehicleId(vehicleId)
        journal.registerRepair(RepairDetails(description, cost, date))
        repository.save(journal)
    }

    override fun registerCheckout(vehicleId: Long, checkoutDate: LocalDate, expirationDate: LocalDate, result: CheckoutResult) {
        val journal = repository.findOneByVehicleId(vehicleId)
        journal.registerCheckout(checkoutDate, expirationDate, result)
        repository.save(journal)
    }

}

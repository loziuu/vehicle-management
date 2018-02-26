package pl.loziuu.ivms.maintenance.application

import pl.loziuu.ivms.ddd.ApplicationService
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutFactory
import pl.loziuu.ivms.maintenance.checkout.domain.CheckoutResult
import pl.loziuu.ivms.maintenance.checkout.ports.CheckoutService
import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceFactory
import pl.loziuu.ivms.maintenance.insurance.ports.InsuranceService
import pl.loziuu.ivms.maintenance.journal.domain.Journal
import pl.loziuu.ivms.maintenance.journal.domain.JournalRepository
import pl.loziuu.ivms.maintenance.journal.port.JournalService
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.maintenance.repair.ports.RepairService
import java.time.LocalDate
import javax.transaction.Transactional

@Transactional
@ApplicationService
class MaintenanceService(val repository: JournalRepository) : JournalService, InsuranceService, RepairService, CheckoutService {
    override fun setupJournal(vehicleId: Long): Long = repository.save(Journal(vehicleId = vehicleId)).id

    override fun removeJournal(vehicleId: Long) = repository.deleteByVehicleId(vehicleId)

    override fun registerInsurance(vehicleId: Long, startDate: LocalDate, endDate: LocalDate, companyName: String) {
        val journal = repository.findOneByVehicleId(vehicleId)
        val insurance = InsuranceFactory.create(startDate, endDate, companyName)
        journal.registerInsurance(insurance)
        repository.save(journal)
    }


    override fun removeInsurance(vehicleId: Long, insuranceId: Long) {
        val journal = repository.findOneByVehicleId(vehicleId)
        journal.removeInsurance(insuranceId)
        repository.save(journal)
    }

    override fun registerRepair(vehicleId: Long, description: String, date: LocalDate, cost: Double) {
        val journal = repository.findOneByVehicleId(vehicleId)
        journal.registerRepair(RepairDetails(description, cost, date))
        repository.save(journal)
    }

    override fun removeRepair(vehicleId: Long, repairId: Long) {
        val journal = repository.findOneByVehicleId(vehicleId)
        journal.removeRepair(repairId)
        repository.save(journal)
    }

    override fun registerCheckout(vehicleId: Long, checkoutDate: LocalDate, expirationDate: LocalDate, result: CheckoutResult) {
        val journal = repository.findOneByVehicleId(vehicleId)
        val checkout = CheckoutFactory.create(checkoutDate, expirationDate, result)
        journal.registerCheckout(checkout)
        repository.save(journal)
    }


    override fun removeCheckout(vehicleId: Long, checkoutId: Long) {
        val journal = repository.findOneByVehicleId(vehicleId)
        journal.removeCheckout(checkoutId)
        repository.save(journal)
    }
}

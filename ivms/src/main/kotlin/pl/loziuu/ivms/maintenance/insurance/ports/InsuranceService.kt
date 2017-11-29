package pl.loziuu.ivms.maintenance.insurance.ports

import java.time.LocalDate

interface InsuranceService {
    fun registerInsurance(vehicleId: Long, startDate: LocalDate, endDate: LocalDate, companyName: String)
}
package pl.loziuu.ivms.maintenance.insurance.ports

import java.time.LocalDate

interface InsuranceService {
    fun registerInsurance(vehicleId: Long, startDate: LocalDate, endDate: LocalDate, companyName: String)
    fun removeInsurance(id: Long, insuranceId: Long)
    fun removeCheckout(id: Long, checkoutId: Long)
    fun removeRepair(id: Long, repairId: Long)
}
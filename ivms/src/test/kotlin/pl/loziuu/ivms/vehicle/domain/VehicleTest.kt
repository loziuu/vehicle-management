package pl.loziuu.ivms.vehicle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.insurance.domain.InsuranceDto
import pl.loziuu.ivms.insurance.domain.InsuranceFactory
import pl.loziuu.ivms.repair.domain.Repair
import pl.loziuu.ivms.repair.domain.RepairDetails
import java.time.LocalDate

class VehicleTest {

    @Test
    fun testRepairsSum() {
        val vehicle = Vehicle()
        vehicle.postRepair(Repair(details = RepairDetails(cost = 150.0)))
        vehicle.postRepair(Repair(details = RepairDetails(cost = 150.0)))

        val sum = vehicle.sumRepairsCost()

        assertThat(sum).isEqualTo(300.0)
    }

    @Test
    fun vehicleWithValidInsuranceShouldBeInsured() {
        val vehicle = Vehicle()
        vehicle.postInsurance(InsuranceFactory.create(InsuranceDto(company = "Company", endDate = LocalDate.parse("2050-01-01"))))

        assertThat(vehicle.isInsured()).isTrue()
    }

    @Test
    fun vehicleWithExpiredInsuranceShouldNotBeInsured() {
        val vehicle = Vehicle()
        vehicle.postInsurance(InsuranceFactory.create(InsuranceDto(company = "Company",
                startDate = LocalDate.parse("2000-01-01"),
                endDate = LocalDate.parse("2001-01-01"))))

        assertThat(vehicle.isInsured()).isFalse()
    }

    @Test
    fun vehicleWithNoInsurancesShouldNotBeInsured() {
        val vehicle = Vehicle()

        assertThat(vehicle.isInsured()).isFalse()
    }
}
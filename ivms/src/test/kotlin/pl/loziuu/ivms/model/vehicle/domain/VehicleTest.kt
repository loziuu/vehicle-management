package pl.loziuu.ivms.model.vehicle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.model.repair.domain.Repair
import pl.loziuu.ivms.model.repair.domain.RepairDetails

class VehicleTest {

    @Test
    fun testRepairsSum() {
        val vehicle = Vehicle()
        vehicle.addRepair(Repair(details = RepairDetails(cost = 150.0)))
        vehicle.addRepair(Repair(details = RepairDetails(cost = 150.0)))

        val sum = vehicle.sumRepairsCost()

        assertThat(sum).isEqualTo(300.0)
    }
}
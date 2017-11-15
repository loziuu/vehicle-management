package pl.loziuu.ivms.management.fleet.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails

class FleetTest {

    @Test
    fun shouldCreateFleet() {
        val fleet = Fleet()

        assertThat(fleet).isNotNull()
    }

    @Test
    fun shouldAddVehicleToFleet() {
        val fleet = Fleet()

        fleet.addVehicle(VehicleDetails())

        assertThat(fleet.vehicles).hasSize(1)
    }

    @Test
    fun addVehicleShouldReturnVehicleByLocalIdentity() {
        val vehicle = VehicleDetails()
        val fleet = Fleet()

        fleet.addVehicle(vehicle)

        assertThat(fleet.getVehicle(1L).details).isEqualTo(vehicle)
    }
}
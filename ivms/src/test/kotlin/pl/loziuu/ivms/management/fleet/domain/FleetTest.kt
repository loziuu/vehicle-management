package pl.loziuu.ivms.management.fleet.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.management.vehicle.domain.Vehicle
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

        fleet.addVehicle(Vehicle())

        assertThat(fleet.vehicles).hasSize(1)
    }

    @Test
    fun addVehicleShouldReturnVehicleByLocalIdentity() {
        val vehicle = Vehicle()
        val fleet = Fleet()

        fleet.addVehicle(vehicle)

        assertThat(fleet.getVehicle(1L).details).isEqualTo(vehicle.details)
    }
}
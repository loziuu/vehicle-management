package pl.loziuu.ivms.vehicle.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.infrastructure.exceptions.ValidationException

class VehicleFactoryTest {

    @Test
    fun validDetailsShouldReturnVehicle() {
        val vehicle = VehicleFactory.create(VehicleDetails("Model", "Manufacturer", 2000))

        assertThat(vehicle).isInstanceOf(Vehicle::class.javaObjectType)
    }

    @Test(expected = ValidationException::class)
    fun blankModelShouldThrowException() {
        VehicleFactory.create(VehicleDetails("", "Manufacturer", 2000))
    }

    @Test(expected = ValidationException::class)
    fun blankManufacturerShouldThrowException() {
        VehicleFactory.create(VehicleDetails("Model", "", 2000))
    }

    @Test(expected = ValidationException::class)
    fun invalidProductionYearShouldThrowException() {
        VehicleFactory.create(VehicleDetails("Model", "", 1950))
    }
}
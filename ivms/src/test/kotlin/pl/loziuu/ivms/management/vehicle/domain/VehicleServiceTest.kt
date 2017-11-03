package pl.loziuu.ivms.management.vehicle.domain

import junit.framework.Assert.assertNotNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import pl.loziuu.ivms.management.vehicle.exception.VehicleNotFoundException
import pl.loziuu.ivms.management.vehicle.ports.secondary.VehicleRepository
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleService

@DataJpaTest
@RunWith(SpringRunner::class)
class VehicleServiceTest {

    @Autowired
    lateinit var repository: VehicleRepository
    lateinit var service: VehicleService

    @Before
    fun setup() {
        service = VehicleServiceImpl(repository)
    }

    @Test
    fun shouldAddVehicleAndReturnId() {
        val id = service.add(VehicleDetails("Model", "Manufacturer", 2000))

        assertNotNull(id)
        assertThat(repository.findOne(id)).isNotNull()
    }

    @Test
    fun shouldDeleteEntity() {
        service.delete(2L)

        assertThat(repository.findOne(2L)).isNull()
    }

    @Test(expected = VehicleNotFoundException::class)
    fun deleteNonExistingEntityShouldThrowException() {
        service.delete(100L)
    }
}
package pl.loziuu.ivms.model.vehicle.domain

import junit.framework.Assert.assertNotNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import pl.loziuu.ivms.model.vehicle.exception.VehicleNotFoundException

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
    fun shouldAddVehicleAndReturnDto() {
        val dto = service.add(VehicleDetails("Model", "Manufacturer", 2000))

        assertNotNull(dto)
        assertThat(repository.findAll().size).isEqualTo(3)
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
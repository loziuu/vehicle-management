package pl.loziuu.ivms.model.vehicle.query

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
class VehicleQueryServiceTest {

    @Autowired
    lateinit var repository: VehicleQueryRepository
    lateinit var service: VehicleQueryService;

    @Before
    fun setup() {
        service = VehicleQueryServiceImpl(repository)
    }

    @Test
    fun getAllShouldReturnListOfEntities() {
        val result = service.getAll()

        assertThat(result).hasSize(2)
    }

    @Test
    fun getExistingEntityShouldReturnQueryDto() {
        val dto = service.get(1L)
        assertThat(dto).isNotNull();
    }

    @Test(expected = VehicleNotFoundException::class)
    fun getNonExistingEntityShouldThrowException() {
        service.get(100L)
    }
}
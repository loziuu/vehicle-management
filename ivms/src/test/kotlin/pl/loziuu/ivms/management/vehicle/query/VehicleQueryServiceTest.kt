package pl.loziuu.ivms.management.vehicle.query

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import pl.loziuu.ivms.management.vehicle.exception.VehicleNotFoundException
import pl.loziuu.ivms.management.vehicle.ports.secondary.VehicleQueryRepository
import pl.loziuu.ivms.management.vehicle.ports.primary.VehicleQueryService

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

        print(dto.insurances)
        println(dto.repairs)
        assertThat(dto).isNotNull();
        assertThat(dto.insurances).isNotEmpty
        assertThat(dto.repairs).isNotEmpty
    }

    @Test(expected = VehicleNotFoundException::class)
    fun getNonExistingEntityShouldThrowException() {
        service.get(100L)
    }

    @Test
    fun getAllRepairsFromVehicleQueryDtoShouldReturn2Repairs() {
        val vehicle = service.get(1L)

        assertThat(vehicle.repairs.size).isEqualTo(2);
    }

    @Test
    fun getInsuredVehicles() {
        val insuredVehicles = service.getInsured();

        assertThat(insuredVehicles).isNotEmpty
    }
}
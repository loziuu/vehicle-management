package pl.loziuu.ivms.model.insurance.query

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@DataJpaTest
@RunWith(SpringRunner::class)
class InsuranceQueryServiceTest {

    @Autowired
    lateinit var repository: InstanceQueryRepository
    lateinit var service: InsuranceQueryService

    @Before
    fun setup() {
        service = InsuranceQueryServiceImpl(repository)
    }

    @Test
    fun shouldReturnAllInsuranceForVehicle() {
        val insurances = service.getVehicleInsurances(1L)

        assertThat(insurances).isNotNull()
        assertThat(insurances.size).isEqualTo(2)
    }

    @Test
    fun getShouldReturnInsurance() {
        val insurance = service.get(1L)

        assertThat(insurance).isNotNull()
        assertThat(insurance.vehicleId).isEqualTo(1L)
    }
}
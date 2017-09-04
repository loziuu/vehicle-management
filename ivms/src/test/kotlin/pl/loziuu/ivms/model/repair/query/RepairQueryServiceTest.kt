package pl.loziuu.ivms.model.insurance.query

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import pl.loziuu.ivms.model.repair.query.RepairQueryRepository
import pl.loziuu.ivms.model.repair.query.RepairQueryService
import pl.loziuu.ivms.model.repair.query.RepairQueryServiceImpl

@DataJpaTest
@RunWith(SpringRunner::class)
class RepairQueryServiceTest {

    @Autowired
    lateinit var repository: RepairQueryRepository
    lateinit var service: RepairQueryService

    @Before
    fun setup() {
        service = RepairQueryServiceImpl(repository)
    }

    @Test
    fun shouldReturnAllRepairsForVehicle() {
        val repairs = service.getVehicleRepairs(1L)

        assertThat(repairs).isNotNull()
        assertThat(repairs.size).isEqualTo(2)
    }
}
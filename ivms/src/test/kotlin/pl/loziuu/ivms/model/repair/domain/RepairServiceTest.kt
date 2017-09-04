package pl.loziuu.ivms.model.repair.domain

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import pl.loziuu.ivms.model.repair.domain.RepairDto
import pl.loziuu.ivms.model.repair.domain.RepairRepository
import pl.loziuu.ivms.model.repair.domain.RepairService
import pl.loziuu.ivms.model.repair.domain.RepairServiceImpl


@DataJpaTest
@RunWith(SpringRunner::class)
class RepairServiceTest {

    @Autowired
    lateinit var repository: RepairRepository;
    lateinit var service: RepairService

    @Before
    fun setup() {
        service = RepairServiceImpl(repository)
    }

    @Test
    fun shouldAddNewRepair() {
        service.add(RepairDto(0, "Nowa naprawa", 120.0, 1L))

        assertThat(repository.findAll().size).isEqualTo(3)
    }
}

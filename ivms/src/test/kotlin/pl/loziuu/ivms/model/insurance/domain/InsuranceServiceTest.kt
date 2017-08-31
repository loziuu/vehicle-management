package pl.loziuu.ivms.model.insurance.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate

@DataJpaTest
@RunWith(SpringRunner::class)
class InsuranceServiceTest {

    @Autowired
    lateinit var repository: InsuranceRepository
    lateinit var service: InsuranceService

    @Before
    fun setup() {
        service = InsuranceServiceImpl(repository)
    }

    @Test
    fun shouldAddInsurance() {
        service.add(InsuranceDto(0, LocalDate.now(), LocalDate.now(), 1));

        assertThat(repository.findAll().size).isEqualTo(3)
    }

    @Test
    fun shouldDeleteInsurance() {
        service.delete(1L);

        assertThat(repository.findOne(1L)).isNull();
    }
}
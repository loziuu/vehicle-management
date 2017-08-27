package pl.loziuu.ivms.model.insurance.domain

import junit.framework.Assert.assertNotNull
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

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
        service.add(InsuranceDto());

        assertThat(repository.findAll().size).isEqualTo(3)
    }
}
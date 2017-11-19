package pl.loziuu.ivms.maintenance.application

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class FleetResolverTest {

    @Autowired lateinit var resolver : FleetResolver

    @Test
    fun getVehicleIdsForFleet() {
        val ids : List<Long> = resolver.getVehicleIdsForFleet(1L)

        assertThat(ids).hasSize(2)
    }
}
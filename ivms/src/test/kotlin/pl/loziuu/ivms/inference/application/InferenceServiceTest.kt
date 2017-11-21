package pl.loziuu.ivms.inference.application

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class InferenceServiceTest {

    @Autowired
    lateinit var service: InferenceService

    @Test
    fun getFleetStatus() {
        print(service.getFleetStatus(1L))
    }
}
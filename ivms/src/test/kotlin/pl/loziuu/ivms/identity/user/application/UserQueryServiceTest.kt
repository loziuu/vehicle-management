package pl.loziuu.ivms.identity.user.application

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@RunWith(SpringRunner::class)
class UserQueryServiceTest {

    @Autowired
    lateinit var service: UserQueryService

    @Test
    fun getAllShouldReturnOne() {
        assertThat(service.getAll()).hasSize(1);
    }
}
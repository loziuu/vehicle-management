package pl.loziuu.ivms.presentation

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import javax.transaction.Transactional

@SpringBootTest
@Transactional
@RunWith(SpringRunner::class)
class SearchRestControllerTest {

    @Autowired
    lateinit var controller: SearchRestController

    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build()
    }

    @Test
    fun getInsuredShouldReturnInsurancedVehicles() {
        mockMvc.perform(get("/v1/vehicles/search/is_insured"))
                .andExpect(status().isOk())
    }

    @Test
    fun getUninsuredShouldReturnInsurancedVehicles() {
        mockMvc.perform(get("/v1/vehicles/search/is_uninsured"))
                .andExpect(status().isOk())
    }
}
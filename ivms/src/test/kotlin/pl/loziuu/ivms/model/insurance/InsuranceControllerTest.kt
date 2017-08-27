package pl.loziuu.ivms.model.insurance

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@Transactional
@RunWith(SpringRunner::class)
class InsuranceControllerTest {

    @Autowired
    lateinit var controller: InsuranceController
    @Autowired
    lateinit var contenxt: WebApplicationContext
    @Autowired
    lateinit var jsonMapper: ObjectMapper
    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(contenxt)
                .build()
    }

    @Test
    fun getVehicleInsurancesShouldReturnOk() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/1/insurances"))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun getVehicleInsurancesOfNonExistingVehicleShouldReturnNotFound() {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/100/insurances"))
                .andExpect(MockMvcResultMatchers.status().isNotFound)
    }
}
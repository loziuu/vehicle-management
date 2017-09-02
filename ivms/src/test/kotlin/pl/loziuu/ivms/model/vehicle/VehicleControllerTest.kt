package pl.loziuu.ivms.model.vehicle

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import pl.loziuu.ivms.model.insurance.domain.InsuranceDto
import pl.loziuu.ivms.model.vehicle.domain.VehicleDto

@SpringBootTest
@Transactional
@RunWith(SpringRunner::class)
class VehicleControllerTest {

    @Autowired
    lateinit var controller: VehicleController
    @Autowired
    lateinit var jsonMapper: ObjectMapper
    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build()
    }

    @Test
    fun getAllVehiclesShouldReturnJsonAndOk() {
        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }

    @Test
    fun getOneShouldReturnJsonAndOk() {
        mockMvc.perform(get("/vehicles/1"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }

    @Test
    fun getNonExistingShouldReturnNotFound() {
        mockMvc.perform(get("/vehicles/100"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun validPostShouldAddVehicleAndReturnCreated() {
        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(VehicleDto())))
                .andExpect(status().isCreated)
    }

    @Test
    fun invalidPostShouldReturnBadRequest() {
        mockMvc.perform(post("/vehicles")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun deleteEntityShouldReturnNoContent() {
        mockMvc.perform(delete("/vehicles/1"))
                .andExpect(status().isNoContent)
    }

    @Test
    fun deleteNonExistingEntityShouldReturnNotFound() {
        mockMvc.perform(delete("/vehicles/100"))
                .andReturn()
    }


    @Test
    fun addInsuranceToVehicleShouldReturnCreated() {
        mockMvc.perform(post("/vehicles/1/insurances")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(InsuranceDto())))
                .andExpect(status().isCreated)
    }

    @Test
    fun getVehicleInsurancesShouldReturnOk() {
        mockMvc.perform(get("/vehicles/1/insurances"))
                .andExpect(status().isOk)
    }

    @Test
    fun deleteExistingInsuranceShouldReturnNoContent() {
        mockMvc.perform(delete("/vehicles/1/insurances/1"))
                .andExpect(status().isNoContent)
    }

    @Test
    fun getRepairsForVehicle() {
        mockMvc.perform(get("/vehicles/1/repairs"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(2))
    }
}

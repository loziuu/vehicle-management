package pl.loziuu.ivms.presentation

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
import pl.loziuu.ivms.maintenance.insurance.domain.InsuranceDto
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import java.time.LocalDate

@SpringBootTest
@Transactional
@RunWith(SpringRunner::class)
class VehicleRestEndpointTest {

    @Autowired
    lateinit var controller: VehicleRestController
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
        mockMvc.perform(get("/v1/vehicles"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        }

    @Test
    fun getOneShouldReturnJsonAndOk() {
        mockMvc.perform(get("/v1/vehicles/1"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }

    @Test
    fun getNonExistingShouldReturnNotFound() {
        mockMvc.perform(get("/v1/vehicles/100"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun validPostShouldAddVehicleAndReturnCreated() {
        mockMvc.perform(post("/v1/vehicles")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(VehicleDetails("Model", "Manufacturer", 2000))))
                .andExpect(status().isCreated)
    }

    @Test
    fun invalidPostShouldReturnBadRequest() {
        mockMvc.perform(post("/v1/vehicles")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest)
    }

    @Test
    fun deleteEntityShouldReturnNoContent() {
        mockMvc.perform(delete("/v1/vehicles/1"))
                .andExpect(status().isNoContent)
    }

    @Test
    fun deleteNonExistingEntityShouldReturnNotFound() {
        mockMvc.perform(delete("/v1/vehicles/100"))
                .andReturn()
    }

    @Test
    fun addInsuranceToVehicleShouldReturnCreated() {
        mockMvc.perform(post("/v1/vehicles/1/insurances")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(InsuranceDto(company = "Company"))))
                .andExpect(status().isCreated)
    }

    @Test
    fun getVehicleInsurancesShouldReturnOk() {
        mockMvc.perform(get("/v1/vehicles/1/insurances"))
                .andExpect(status().isOk)
    }

    @Test
    fun deleteExistingInsuranceShouldReturnNoContent() {
        mockMvc.perform(delete("/v1/vehicles/1/insurances/1"))
                .andExpect(status().isNoContent)
    }

    @Test
    fun getRepairsForVehicleShouldReturnOk() {
        mockMvc.perform(get("/v1/vehicles/1/repairs"))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$.length()").value(2))
    }

    @Test
    fun postShouldAddNewRepairShoudReturnCreated() {
        val repairDto = RepairDetails("0", 120.0, LocalDate.now())

        mockMvc.perform(post("/v1/vehicles/1/repairs")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonMapper.writeValueAsString(repairDto)))
                .andExpect(status().isCreated)
    }

    @Test
    fun getOneVehicleRepairShouldReturnOk() {
        mockMvc.perform(get("/v1/vehicles/1/repairs/1"))
                .andExpect(status().isOk)
    }

    @Test
    fun getNonExistingVehicleRepairShouldReturnNotFound() {
        mockMvc.perform(get("/v1/vehicles/1/repairs/100"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun getVehicleInsuranceShouldReturnOk() {
        mockMvc.perform(get("/v1/vehicles/1/insurances/1"))
                .andExpect(status().isOk)
    }

    @Test
    fun getNonExistingVehicleInsuranceShouldReturnNotFound() {
        mockMvc.perform(get("/v1/vehicles/1/insurances/100"))
                .andExpect(status().isNotFound)
    }
}

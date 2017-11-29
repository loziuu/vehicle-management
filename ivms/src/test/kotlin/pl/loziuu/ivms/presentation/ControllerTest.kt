package pl.loziuu.ivms.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import pl.loziuu.ivms.management.vehicle.domain.VehicleDetails
import pl.loziuu.ivms.presentation.dtos.CreateFleetDto
import pl.loziuu.ivms.presentation.dtos.RegisterCheckoutDto
import pl.loziuu.ivms.presentation.dtos.RegisterInsuranceDto
import pl.loziuu.ivms.presentation.dtos.RegisterRepairDto

@SpringBootTest
@Transactional
@RunWith(SpringRunner::class)
class ControllerTest {

    @Autowired
    lateinit var jackson2HttpMessageConverter: MappingJackson2HttpMessageConverter

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var webCtx: WebApplicationContext

    lateinit var mockMvc: MockMvc
    lateinit var restul: MvcResult

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webCtx).build()
    }

    @Test
    fun getFleets() {
        mockMvc.perform(get("/api/v1/fleets"))
                .andExpect(status().isOk)
    }

    @Test
    fun getFleet() {
        mockMvc.perform(get("/api/v1/fleets/1"))
                .andExpect(status().isOk)
    }

    @Test
    fun getFleetVehicles() {
        mockMvc.perform(get("/api/v1/fleets/1/vehicles"))
                .andExpect(status().isOk)
    }

    @Test
    fun getFleetVehicle() {
        mockMvc.perform(get("/api/v1/fleets/1/vehicles/1"))
                .andExpect(status().isOk)
    }

    @Test
    fun postFleet() {
        mockMvc.perform(post("/api/v1/fleets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CreateFleetDto())))
                .andExpect(status().isOk)
    }

    @Test
    fun postFleetVehicle() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VehicleDetails())))
                .andExpect(status().isOk)
    }

    @Test
    fun postVehicleInsurance() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles/1/insurances")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterInsuranceDto())))
                .andExpect(status().isOk)
    }

    @Test
    fun postVehicleRepair() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles/1/repairs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterRepairDto())))
                .andExpect(status().isOk)
    }

    @Test
    fun postVehicleCheckout() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles/1/checkouts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterCheckoutDto())))
                .andExpect(status().isOk)
    }
}

package pl.loziuu.ivms.presentation

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
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
@WithMockUser(roles = arrayOf("ADMIN"))
class FleetControllerTest {

    @Autowired
    lateinit var jackson2HttpMessageConverter: MappingJackson2HttpMessageConverter

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var webCtx: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webCtx)
                .apply<DefaultMockMvcBuilder>(springSecurity())
                .build()
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
        mockMvc.perform(post("/api/v1/fleets").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CreateFleetDto(name = "Test"))))
                .andExpect(status().isOk)
    }

    @Test
    @WithMockUser(roles = arrayOf("VISITOR"))
    fun visitorTryingToCreateFleetShouldBeDenied() {
        mockMvc.perform(post("/api/v1/fleets").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(CreateFleetDto())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().`is`(403))
    }

    @Test
    fun postFleetVehicle() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(VehicleDetails(manufacturer = "Test", model = "Test", productionYear = 2012))))
                .andExpect(status().isOk)
    }

    @Test
    fun postVehicleInsurance() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles/1/insurances").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterInsuranceDto(company = "Test"))))
                .andExpect(status().isOk)
    }

    @Test
    fun postVehicleRepair() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles/1/repairs").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterRepairDto())))
                .andExpect(status().isOk)
    }

    @Test
    fun postVehicleCheckout() {
        mockMvc.perform(post("/api/v1/fleets/1/vehicles/1/checkouts").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterCheckoutDto())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
    }

    @Test
    fun getFutureFleet() {
        mockMvc.perform(get("/api/v1/fleets/1/2080-01-01").with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RegisterCheckoutDto())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
    }
}

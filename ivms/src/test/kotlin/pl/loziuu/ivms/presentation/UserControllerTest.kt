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
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import pl.loziuu.ivms.presentation.user.requests.ChangePasswordRequest
import pl.loziuu.ivms.presentation.user.requests.NewUserRequest

@SpringBootTest
@Transactional
@RunWith(SpringRunner::class)
@WithMockUser(roles = arrayOf("ADMIN"))
class UserControllerTest {

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
                .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
                .build()
    }

    @Test
    fun getAllUsers() {
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun addNewUser() {
        mockMvc.perform(post("/api/v1/users").with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(NewUserRequest(login = "test", password = "test")))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
    }

    @Test
    @WithMockUser(roles = arrayOf("VISITOR"))
    fun addNewUserWithNotPermmitedRoleShouldReturn403() {
        mockMvc.perform(post("/api/v1/users").with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(NewUserRequest(login = "test", password = "test")))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().`is`(403))
    }


    @Test
    @WithMockUser(roles = arrayOf("VISITOR"))
    fun changePasswordWithNotPermmitedRoleShouldReturn403() {
        mockMvc.perform(put("/api/v1/users/1/change-password").with(csrf())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsBytes(ChangePasswordRequest(password = "test")))
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().`is`(403))
    }
}
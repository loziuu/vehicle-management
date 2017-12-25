package pl.loziuu.ivms.presentation.security.handlers

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SuccessLoginHandler : SavedRequestAwareAuthenticationSuccessHandler() {
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        response.status = HttpServletResponse.SC_OK
    }
}
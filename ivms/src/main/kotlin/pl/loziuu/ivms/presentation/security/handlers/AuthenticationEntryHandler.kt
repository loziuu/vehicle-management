package pl.loziuu.ivms.presentation.security.handlers

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationEntryHandler : LoginUrlAuthenticationEntryPoint("/login") {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException?) {
        super.commence(request, response, authException)
        response.status = 401
    }
}
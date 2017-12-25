package pl.loziuu.ivms.presentation.security.handlers

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import java.io.PrintWriter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginFailureHandler : SimpleUrlAuthenticationFailureHandler() {
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException) {
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        printErrorMessage(response.writer, exception)
    }

    private fun printErrorMessage(writer: PrintWriter, exception: AuthenticationException) {
        writer.write(exception.message)
        writer.flush()
    }
}
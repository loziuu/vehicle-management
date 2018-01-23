package pl.loziuu.ivms.presentation.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import pl.loziuu.ivms.presentation.security.handlers.LoginFailureHandler
import pl.loziuu.ivms.presentation.security.handlers.SuccessLoginHandler
import javax.sql.DataSource

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var dataSource: DataSource

    override fun configure(auth: AuthenticationManagerBuilder?) {
        if (auth != null) {
            auth
                    .jdbcAuthentication()
//                    .passwordEncoder(BCryptPasswordEncoder())
                    .dataSource(dataSource)
                    .usersByUsernameQuery("SELECT u.login, u.password, u.enabled " +
                            "FROM users u " +
                            "WHERE u.login=?")
                    .authoritiesByUsernameQuery("SELECT u.login, u.role FROM users u WHERE u.login=?")
                    .rolePrefix("ROLE_")
        }
    }

    override fun configure(http: HttpSecurity) {
        super.configure(http)

        http.cors()

        http.headers().cacheControl()

        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

//        http.exceptionHandling().authenticationEntryPoint(AuthenticationEntryHandler())

        http.formLogin()
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(SuccessLoginHandler())
                .failureHandler(LoginFailureHandler())
                .permitAll()
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .clearAuthentication(true)
                .permitAll()
                .and()
                .sessionManagement().maximumSessions(1)

        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated()
    }
}
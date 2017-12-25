package pl.loziuu.ivms.presentation.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class WebConfiguration : WebMvcConfigurerAdapter() {

    override fun addCorsMappings(registry: CorsRegistry?) {
        registry!!.addMapping("/**")
                .allowedMethods("OPTIONS", "GET", "PUT", "POST", "DELETE")
                .allowedOrigins("http://localhost:4200")
    }
}
package pl.loziuu.ivms.model.repair.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RepairConfiguration {

    @Autowired
    lateinit var repository: RepairRepository

    @Bean
    fun repairService() =
            RepairServiceImpl(repository)
}
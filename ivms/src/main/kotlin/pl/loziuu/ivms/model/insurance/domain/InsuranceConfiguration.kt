package pl.loziuu.ivms.model.insurance.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.loziuu.ivms.model.insurance.query.InstanceQueryRepository
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryService
import pl.loziuu.ivms.model.insurance.query.InsuranceQueryServiceImpl

@Configuration
class InsuranceConfiguration {

    @Bean
    fun insuranceService(repository: InsuranceRepository): InsuranceService
            = InsuranceServiceImpl(repository)

    @Bean
    fun insuranceQueryService(repository: InstanceQueryRepository): InsuranceQueryService
            = InsuranceQueryServiceImpl(repository)

    @Bean
    fun insuranceFacade(commandRepository: InsuranceRepository, queryRepository: InstanceQueryRepository): InsuranceFacade {
        val command = InsuranceServiceImpl(commandRepository)
        val query = InsuranceQueryServiceImpl(queryRepository)
        return InsuranceFacade(command, query)
    }
}
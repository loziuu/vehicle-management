package pl.loziuu.ivms.maintenance.repair

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.ddd.DomainValidationException
import pl.loziuu.ivms.management.infrastructure.exceptions.ValidationException
import pl.loziuu.ivms.maintenance.repair.domain.Repair
import pl.loziuu.ivms.maintenance.repair.domain.RepairDetails
import pl.loziuu.ivms.maintenance.repair.domain.RepairFactory

class RepairFactoryTest {

    @Test
    fun validDetailsShouldReturnRepair() {
        val repair = RepairFactory.create(RepairDetails(cost = 0.0))

        assertThat(repair).isInstanceOf(Repair::class.javaObjectType)
    }

    @Test(expected = DomainValidationException::class)
    fun costIsNegativeShouldThrowException() {
        RepairFactory.create(RepairDetails(cost = -200.0))
    }
}
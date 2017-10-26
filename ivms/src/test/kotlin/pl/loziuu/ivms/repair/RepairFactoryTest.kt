package pl.loziuu.ivms.repair

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pl.loziuu.ivms.infrastructure.exceptions.ValidationException
import pl.loziuu.ivms.repair.domain.Repair
import pl.loziuu.ivms.repair.domain.RepairDetails
import pl.loziuu.ivms.repair.domain.RepairFactory

class RepairFactoryTest {

    @Test
    fun validDetailsShouldReturnRepair() {
        val repair = RepairFactory.create(RepairDetails(cost = 0.0))

        assertThat(repair).isInstanceOf(Repair::class.javaObjectType)
    }

    @Test(expected = ValidationException::class)
    fun costIsNegativeShouldThrowException() {
        RepairFactory.create(RepairDetails(cost = -200.0))
    }
}
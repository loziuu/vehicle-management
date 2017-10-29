package pl.loziuu.ivms.vehicle.domain

import fuzzy4j.flc.ControllerBuilder
import fuzzy4j.flc.InputInstance
import fuzzy4j.flc.Term
import fuzzy4j.flc.Variable
import fuzzy4j.sets.FuzzyFunction
import fuzzy4j.sets.SetsFactory
import fuzzy4j.sets.TriangularFunction
import org.aspectj.weaver.ast.Var
import org.assertj.core.api.Assertions.`in`
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FuzzyLogicTest {

    @Test
    fun testCreationOfTriangularFunction() {
        val function : FuzzyFunction = TriangularFunction(0.0, 3.0, 6.0)

        assertThat(function.apply(0.0)).isEqualTo(0.0)
        assertThat(function.apply(3.0)).isEqualTo(1.0)
        assertThat(function.apply(6.0)).isEqualTo(0.0)
    }

    @Test
    fun createNewTerm() {
        // Number of vehicles in next week
        val small : Term = Term.term("small", 0.0, 1.0, 3.0)
        val med : Term = Term.term("med", 1.0, 3.0, 5.0)
        val big : Term = Term.term("big", 3.0, 10.0, 10.0)

        val checkouts : Variable = Variable.input("checkouts", small, med, big).start(10.0).end(100.0);

        var low = Term.term("Low", 0.0, 25.0, 50.0)
        var high = Term.term("High", 25.0, 75.0, 100.0)

        val effort : Variable = Variable.input("effort", low, high).start(0.0).end(100.0)

        val impl = ControllerBuilder.newBuilder()
                .`when`().`var`(checkouts).`is`(small).then().`var`(effort).`is`(low)
                .`when`().`var`(checkouts).`is`(med).then().`var`(effort).`is`(low)
                .`when`().`var`(checkouts).`is`(big).then().`var`(effort).`is`(high)
                .create()

        val input = InputInstance().`is`(checkouts, 11.0)

        print(impl.applyFuzzy(input))

        val result = impl.apply(input);

        System.out.println("crisp = " + result);
    }
}
package pl.loziuu.ivms.inference.fuzzy

import fuzzy4j.flc.*
import pl.loziuu.ivms.ddd.DomainValidationException
import kotlin.math.roundToInt

object FuzzyLogicService {

    fun getFleetStatus(withoutInsurances: Double, withoutCheckout: Double): Double {

        val zero: Term = Term.term("zero", 0.0, 0.0, 1.0)
        val checkoutLittle: Term = Term.term("little", 0.0, 1.0, 10.0, 15.0)
        val checkoutMedium: Term = Term.term("medium", 10.0, 15.0, 30.0, 35.0)
        val checkoutBig: Term = Term.term("big", 30.0, 35.0, 65.0, 70.0)
        val checkoutHumongous: Term = Term.term("humongous", 65.0, 70.0, 100.0, 100.0)

        val insuranceLittle: Term = Term.term("little", 1.0, 1.0, 15.0, 30.0)
        val insuranceMedium: Term = Term.term("medium", 15.0, 30.0, 40.0, 45.0)
        val insuranceBig: Term = Term.term("big", 40.0, 45.0, 75.0, 80.0)
        val insuranceHumongous: Term = Term.term("humongous", 75.0, 80.0, 100.0, 100.0)

        val checkouts: Variable =
                Variable.input("checkouts", zero, checkoutLittle, checkoutMedium, checkoutBig, checkoutHumongous).start(0.0).end(100.0)

        val insurances: Variable =
                Variable.input("insurances", zero, insuranceLittle, insuranceMedium, insuranceBig, insuranceHumongous).start(0.0).end(100.0)

        val bad: Term = Term.term("bad", 0.0, 0.0, 10.0, 20.0)
        val weak: Term = Term.term("weak", 10.0, 20.0, 30.0)
        val medium: Term = Term.term("medium", 20.0, 30.0, 40.0)
        val good: Term = Term.term("good", 30.0, 40.0, 50.0)
        val perfect: Term = Term.term("perfect", 40.0, 50.0, 50.0, 50.0)

        val fleetStatus: Variable = Variable.input("status", bad, weak, medium, good, perfect).start(0.0).end(50.0)
        val rules = ControllerBuilder.newBuilder()
                .`when`().`var`(checkouts).`is`(zero).and().`var`(insurances).`is`(zero).then().`var`(fleetStatus).`is`(perfect)
                .`when`().`var`(checkouts).`is`(zero).and().`var`(insurances).`is`(insuranceLittle).then().`var`(fleetStatus).`is`(perfect)
                .`when`().`var`(checkouts).`is`(zero).and().`var`(insurances).`is`(insuranceMedium).then().`var`(fleetStatus).`is`(medium)
                .`when`().`var`(checkouts).`is`(zero).and().`var`(insurances).`is`(HedgeBuilder.very(insuranceBig)).then().`var`(fleetStatus).`is`(weak)
                .`when`().`var`(checkouts).`is`(zero).and().`var`(insurances).`is`(insuranceHumongous).then().`var`(fleetStatus).`is`(bad)

                .`when`().`var`(checkouts).`is`(checkoutLittle).and().`var`(insurances).`is`(zero).then().`var`(fleetStatus).`is`(perfect)
                .`when`().`var`(checkouts).`is`(checkoutLittle).and().`var`(insurances).`is`(insuranceLittle).then().`var`(fleetStatus).`is`(good)
                .`when`().`var`(checkouts).`is`(checkoutLittle).and().`var`(insurances).`is`(insuranceMedium).then().`var`(fleetStatus).`is`(medium)
                .`when`().`var`(checkouts).`is`(checkoutLittle).and().`var`(insurances).`is`(insuranceBig).then().`var`(fleetStatus).`is`(weak)
                .`when`().`var`(checkouts).`is`(checkoutLittle).and().`var`(insurances).`is`(insuranceHumongous).then().`var`(fleetStatus).`is`(bad)

                .`when`().`var`(checkouts).`is`(checkoutMedium).and().`var`(insurances).`is`(zero).then().`var`(fleetStatus).`is`(good)
                .`when`().`var`(checkouts).`is`(checkoutMedium).and().`var`(insurances).`is`(insuranceLittle).then().`var`(fleetStatus).`is`(medium)
                .`when`().`var`(checkouts).`is`(checkoutMedium).and().`var`(insurances).`is`(insuranceMedium).then().`var`(fleetStatus).`is`(weak)
                .`when`().`var`(checkouts).`is`(checkoutMedium).and().`var`(insurances).`is`(insuranceBig).then().`var`(fleetStatus).`is`(bad)
                .`when`().`var`(checkouts).`is`(checkoutMedium).and().`var`(insurances).`is`(insuranceHumongous).then().`var`(fleetStatus).`is`(bad)

                .`when`().`var`(checkouts).`is`(checkoutBig).and().`var`(insurances).`is`(zero).then().`var`(fleetStatus).`is`(weak)
                .`when`().`var`(checkouts).`is`(checkoutBig).and().`var`(insurances).`is`(insuranceLittle).then().`var`(fleetStatus).`is`(weak)
                .`when`().`var`(checkouts).`is`(checkoutBig).and().`var`(insurances).`is`(insuranceMedium).then().`var`(fleetStatus).`is`(bad)
                .`when`().`var`(checkouts).`is`(checkoutBig).and().`var`(insurances).`is`(insuranceBig).then().`var`(fleetStatus).`is`(bad)
                .`when`().`var`(checkouts).`is`(checkoutBig).and().`var`(insurances).`is`(insuranceHumongous).then().`var`(fleetStatus).`is`(bad)

                .`when`().`var`(checkouts).`is`(checkoutHumongous).and().`var`(insurances).`is`(zero).then().`var`(fleetStatus).`is`(bad)
                .`when`().`var`(checkouts).`is`(checkoutHumongous).and().`var`(insurances).`is`(insuranceLittle).then().`var`(fleetStatus).`is`(bad)
                .`when`().`var`(checkouts).`is`(checkoutHumongous).and().`var`(insurances).`is`(insuranceMedium).then().`var`(fleetStatus).`is`(bad)
                .`when`().`var`(checkouts).`is`(checkoutHumongous).and().`var`(insurances).`is`(insuranceBig).then().`var`(fleetStatus).`is`(bad)
                .`when`().`var`(checkouts).`is`(checkoutHumongous).and().`var`(insurances).`is`(insuranceHumongous).then().`var`(fleetStatus).`is`(bad);

        val impl = rules.create()

        val map = mutableMapOf<Variable, Double>()
        map.put(insurances, withoutInsurances)
        map.put(checkouts, withoutCheckout)
        val status = impl.apply(InputInstance.wrap(map)).get(fleetStatus)

        if (status != null) {
            return status.roundToInt().toDouble()
        }
        throw DomainValidationException("Can't get Fleet status.");
    }
}
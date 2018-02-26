package pl.loziuu.ivms.inference.fuzzy

import net.sourceforge.jFuzzyLogic.FIS
import net.sourceforge.jFuzzyLogic.rule.Variable
import java.time.LocalDate
import java.time.LocalDateTime

object FuzzyLogicService {

    fun getFleetStatus(withoutInsurances: Double, withoutCheckout: Double): FuzzyRaport {
        val fileName = "fcl/status.fcl"
        val fis = FIS.load(fileName, true)
        fis.setVariable("woCheckout", withoutCheckout)
        fis.setVariable("woInsurance", withoutInsurances)
        fis.evaluate()
        val reStatus = fis.getVariable("status")
        val triggeredRules = fis.getFunctionBlock("status").getFuzzyRuleBlock("No1").rules
                .filter { it -> it.degreeOfSupport > 0.0 }
                .map { it -> it.toString() }
        printFuzzyReportToConsole(withoutCheckout, withoutInsurances, reStatus, triggeredRules)
        val raport = FuzzyRaport(reStatus.value.toInt().toDouble(), triggeredRules)
        return raport
    }

    private fun printFuzzyReportToConsole(withoutCheckout: Double, withoutInsurances: Double, reStatus: Variable?, triggeredRules: List<String>) {
        println("Generated at: " + LocalDateTime.now())
        triggeredRules.forEach { println(it) }
        println("Percentage of vehicles without checkout: " + withoutCheckout)
        println("Percentage of vehicles without insurance: " + withoutInsurances)
        print(reStatus)
    }
}

class FuzzyRaport(val result: Double = 0.0,
                  val triggeredRules: List<String> = emptyList())
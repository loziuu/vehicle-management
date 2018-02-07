package pl.loziuu.ivms.inference.fuzzy

import net.sourceforge.jFuzzyLogic.FIS

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
        val raport = FuzzyRaport(reStatus.value, triggeredRules)
        return raport
    }
}

class FuzzyRaport(val result: Double = 0.0,
                  val triggeredRules: List<String> = emptyList())
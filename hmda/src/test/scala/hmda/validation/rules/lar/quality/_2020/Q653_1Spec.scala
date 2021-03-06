package hmda.validation.rules.lar.quality._2020

import hmda.model.filing.lar.LarGenerators._
import hmda.model.filing.lar.LoanApplicationRegister
import hmda.validation.rules.EditCheck
import hmda.model.filing.lar.enums._
import hmda.validation.rules.lar.LarEditCheckSpec

class Q653_1Spec extends LarEditCheckSpec {
  override def check: EditCheck[LoanApplicationRegister] = Q653_1

  property(
    "CLTV should be between 0 and 250") {
    forAll(larGen) { lar =>
      whenever(List(LoanOriginated, ApplicationApprovedButNotAccepted, PreapprovalRequestApprovedButNotAccepted).contains(lar.action.actionTakenType)){
        val relevantLar = lar.copy(income = "6")
        relevantLar.copy(loan = relevantLar.loan.copy(combinedLoanToValueRatio = "-.01")).mustFail
        relevantLar.copy(loan = relevantLar.loan.copy(combinedLoanToValueRatio = "0")).mustPass
        relevantLar.copy(loan = relevantLar.loan.copy(combinedLoanToValueRatio = "250")).mustPass
        relevantLar.copy(loan = relevantLar.loan.copy(combinedLoanToValueRatio = "250.01")).mustFail
        relevantLar.copy(loan = relevantLar.loan.copy(combinedLoanToValueRatio = "NA")).mustFail
      } 

    }
  }

  property("Must pass if not relevant action taken") {
    forAll(larGen) { lar =>
      whenever(!(List(LoanOriginated, ApplicationApprovedButNotAccepted, PreapprovalRequestApprovedButNotAccepted).contains(lar.action.actionTakenType))){
        lar.mustPass
      }
    }
  }
        
}

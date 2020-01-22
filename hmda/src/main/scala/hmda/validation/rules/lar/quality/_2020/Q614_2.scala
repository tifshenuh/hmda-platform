package hmda.validation.rules.lar.quality.twentytwenty

import hmda.model.filing.lar.LoanApplicationRegister
import hmda.validation.dsl.PredicateCommon._
import hmda.validation.dsl.PredicateSyntax._
import hmda.validation.dsl.ValidationResult
import hmda.validation.rules.EditCheck

object Q614_2 extends EditCheck[LoanApplicationRegister] {
  override def name: String = "Q614-2"

  override def parent: String = "Q614"

  override def apply(lar: LoanApplicationRegister): ValidationResult ={
    val coAge = lar.coApplicant.age

    when(coAge not equalTo(8888)) {
        coAge is between(18, 100)
    }
  }
}

package hmda.validation.engine.lar.syntactical

import hmda.model.fi.lar.LoanApplicationRegister
import hmda.validation.api.ValidationApi
import hmda.validation.engine.lar.LarCommonEngine
import hmda.validation.rules.lar.syntactical._

trait LarSyntacticalEngine extends LarCommonEngine with ValidationApi {

  def checkSyntactical(lar: LoanApplicationRegister): LarValidation = {
    val checks = List(
      S010,
      S020,
      S205
    ).map(check(_, lar))

    validateAll(checks, lar)
  }

  def checkSyntacticalCollection(lars: Iterable[LoanApplicationRegister]): LarsValidation = {
    val checks = List(
      S011,
      S040
    ).map(check(_, lars))

    validateAll(checks, lars)
  }

}
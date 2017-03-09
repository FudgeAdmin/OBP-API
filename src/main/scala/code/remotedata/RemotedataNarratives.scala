package code.remotedata

import akka.pattern.ask
import code.metadata.narrative.{Narrative, RemoteNarrativesCaseClasses}
import code.model._
import scala.concurrent.Await



object RemotedataNarratives extends ActorInit with Narrative {

  val cc = RemoteNarrativesCaseClasses

  def getNarrative(bankId: BankId, accountId: AccountId, transactionId: TransactionId)() : String =
      extractFuture(actor ? cc.getNarrative(bankId, accountId, transactionId))

  def setNarrative(bankId: BankId, accountId: AccountId, transactionId: TransactionId)(narrative: String): Boolean =
    extractFuture(actor ? cc.setNarrative(bankId, accountId, transactionId, narrative))

  def bulkDeleteNarratives(bankId: BankId, accountId: AccountId): Boolean =
    extractFuture(actor ? cc.bulkDeleteNarratives(bankId, accountId))

}
